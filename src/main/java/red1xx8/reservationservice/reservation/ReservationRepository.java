package red1xx8.reservationservice.reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity , Long>, JpaSpecificationExecutor<ReservationEntity> {


    public Page<ReservationEntity> findAll(
            Specification<ReservationEntity> spec,
            Pageable pageable
    );

    @Query("""
       SELECT count(r) > 0 FROM ReservationEntity r
       WHERE (:id IS NULL OR r.id <> :id)
       AND r.table.id = :tableId
       AND r.startReservation < :endReservation
       AND r.endReservation > :startReservation
       AND r.status IN :statuses
""")
    public Boolean tableIsBusy(
            Long id,
            Long tableId,
            LocalDateTime startReservation,
            LocalDateTime endReservation,
            List<ReservationStatus> statuses
    );
}
