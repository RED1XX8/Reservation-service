package red1xx8.reservationservice.reservation;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;

    @PostMapping("/create")
    public ResponseEntity<ReservationResponse> createReservation(
            @RequestBody @Valid ReservationRequest reservationToCreate
    ){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reservationService.createReservation(reservationToCreate));
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<ReservationResponse> changeStatusReservation(
            @PathVariable("id") Long id,
            @RequestBody @Valid ChangeStatusRequest status
    ){
        return ResponseEntity
                .ok(reservationService.changeStatusReservation(id , status));
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<ReservationResponse> updateReservation(
            @PathVariable("id") Long id,
            @RequestBody @Valid ReservationRequest reservationRequest
    ){
        return ResponseEntity
                .ok(reservationService.updateReservation(id , reservationRequest));
    }


    @PostMapping("/search")
    public ResponseEntity<ReservationSliceDto> getReservationByFilter(
        @RequestBody ReservationSearchFilter reservationSearchFilter,
        @PageableDefault(size = 10, sort = "startReservation", direction = Sort.Direction.ASC) Pageable pageable
    ){

        return  ResponseEntity
                .ok(reservationService.searchByFilter(reservationSearchFilter , pageable));
    }

}
