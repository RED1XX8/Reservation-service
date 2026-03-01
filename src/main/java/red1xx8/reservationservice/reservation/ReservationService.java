package red1xx8.reservationservice.reservation;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red1xx8.reservationservice.exception.InvalidTransactionalStatusException;
import red1xx8.reservationservice.table.TableRepository;
import red1xx8.reservationservice.user.UserRepository;


@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper mapper;
    private final UserRepository userRepository;
    private final TableRepository tableRepository;
    private final Logger log = LoggerFactory.getLogger(ReservationService.class);

    @Transactional
    public ReservationResponse createReservation(ReservationRequest reservationToCreate) {

        ReservationEntity entityToSave = mapper.toEntity(reservationToCreate);

        var user= userRepository.findById(reservationToCreate.userId())
                        .orElseThrow(()-> new EntityNotFoundException("User is not found by id:" + reservationToCreate.userId()));

        var table = tableRepository.findById(reservationToCreate.tableId())
                        .orElseThrow(()-> new EntityNotFoundException("Table is not found by id:" + reservationToCreate.tableId()));

        if(reservationToCreate.capacity() > table.getCapacity()){
            throw new IllegalArgumentException("Capacity on the table should have been no more " + table.getCapacity());
        }


        entityToSave.setUser(user);
        entityToSave.setTable(table);
        entityToSave.setStatus(ReservationStatus.PENDING);

        reservationRepository.save(entityToSave);
        log.info("Reservation created: id = {} , userId = {} , tableId = {} , start = {} , end = {}"
                ,entityToSave.getId()
                ,entityToSave.getUser().getId()
                ,entityToSave.getTable().getId()
                ,entityToSave.getStartReservation()
                ,entityToSave.getEndReservation());

        return mapper.toResponse(entityToSave);

    }

    @Transactional
    public  ReservationResponse changeStatusReservation(Long id , ChangeStatusRequest newStatus) {
        var entityToNewStatus = reservationRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Reservation is not found by id:" + id));

        if(!entityToNewStatus.getStatus().canTransactional(newStatus.status())){
            throw new InvalidTransactionalStatusException("Invalid status transition: " +
                    entityToNewStatus.getStatus() + " -> " + newStatus);
        }

        entityToNewStatus.setStatus(newStatus.status());
        log.info("Change reservation status. New status:{}", newStatus );
        return mapper.toResponse(entityToNewStatus);
    }


    @Transactional
    public  ReservationResponse updateReservation(Long id, ReservationRequest reservationRequest) {
        var entityToUpdate = reservationRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Reservation is not found by id:" + id));

        if(!entityToUpdate.getStatus().canBeUpdated()){
            throw new IllegalStateException("This reservation cannot update");
        }

        var table = tableRepository.findById(reservationRequest.tableId())
                .orElseThrow(() -> new EntityNotFoundException("Table for reservation not found by id:" + reservationRequest.tableId()));



        entityToUpdate.setTable(table);
        entityToUpdate.setStartReservation(reservationRequest.startReservation());
        entityToUpdate.setEndReservation(reservationRequest.endReservation());
        entityToUpdate.setComment(reservationRequest.comment());
        entityToUpdate.setEvent(reservationRequest.event());


        log.info("Reservation updated: id = {} , userId = {} , tableId = {} , start = {} , end = {}"
                ,entityToUpdate.getId()
                ,entityToUpdate.getUser().getId()
                ,entityToUpdate.getTable().getId()
                ,entityToUpdate.getStartReservation()
                ,entityToUpdate.getEndReservation());
        return mapper.toResponse(entityToUpdate);


    }


    public ReservationSliceDto searchByFilter(
            ReservationSearchFilter reservationSearchFilter,
            Pageable pageable
    ) {
         var spec = Specification
                 .where(ReservationSpecs.hasReservationId(reservationSearchFilter.id()))
                 .and(ReservationSpecs.hasUserId(reservationSearchFilter.userId()))
                 .and(ReservationSpecs.hasTableId(reservationSearchFilter.tableId()))
                 .and(ReservationSpecs.inRangeTime(
                         reservationSearchFilter.startReservation(),
                         reservationSearchFilter.endReservation()
                 ))
                 .and(ReservationSpecs.hasEvent(reservationSearchFilter.event()))
                 .and(ReservationSpecs.hasStatus(reservationSearchFilter.status()));

         log.info("Search by filter: id={} , userId={}, tableId={}, start={}, end={}, event={}, status={}",
                 reservationSearchFilter.id(),
                 reservationSearchFilter.userId(),
                 reservationSearchFilter.tableId(),
                 reservationSearchFilter.startReservation(),
                 reservationSearchFilter.endReservation(),
                 reservationSearchFilter.event(),
                 reservationSearchFilter.status());

         var page = reservationRepository
                 .findAll(spec , pageable);

         var content = page
                 .getContent()
                 .stream()
                 .map(mapper::toResponse)
                 .toList();

         var hasNext = page.hasNext();

         SliceImpl<ReservationResponse> slice = new SliceImpl<>(content , pageable , hasNext );


        log.info("SearchByFilter returned {} reservations, hasNext={}", content.size(), slice.hasNext());

        return new ReservationSliceDto(
                slice.getContent().stream().toList(),
                slice.getNumber(),
                slice.getSize(),
                slice.hasNext()
        );

    }
}
