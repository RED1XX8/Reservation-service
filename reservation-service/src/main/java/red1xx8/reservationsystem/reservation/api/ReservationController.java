package red1xx8.reservationsystem.reservation.api;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import red1xx8.reservationsystem.reservation.dto.request.ChangeStatusRequest;
import red1xx8.reservationsystem.reservation.dto.request.ReservationRequest;
import red1xx8.reservationsystem.reservation.dto.request.ReservationSearchFilter;
import red1xx8.reservationsystem.reservation.dto.response.ReservationResponse;
import red1xx8.reservationsystem.reservation.dto.response.ReservationSliceDto;
import red1xx8.reservationsystem.reservation.service.ReservationService;


@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<ReservationResponse> createReservation(
            @RequestBody @Valid ReservationRequest reservationToCreate,
            @AuthenticationPrincipal (expression = "id") Long id
    ){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reservationService.createReservation(reservationToCreate , id));
    }


    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN' ,'MAIN_ADMIN')")
    public ResponseEntity<ReservationResponse> changeStatusReservation(
            @PathVariable("id") Long id,
            @RequestBody @Valid ChangeStatusRequest status
    ){
        return ResponseEntity
                .ok(reservationService.changeStatusReservation(id , status));
    }

    @PatchMapping("/{id}/update")
    @PreAuthorize("@reservationSecurity.canUpdate(#id, authentication.principal.id)" +
            "  or hasAnyRole('ADMIN' , 'MAIN_ADMIN')")
    public ResponseEntity<ReservationResponse> updateReservation(
            @RequestBody @Valid ReservationRequest reservationRequest,
            @PathVariable("id")  Long id
    ){
        return ResponseEntity
                .ok(reservationService.updateReservation(id , reservationRequest));
    }


    @PostMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN' ,'MAIN_ADMIN')")
    public ResponseEntity<ReservationSliceDto> searchByFilter(
        @RequestBody ReservationSearchFilter reservationSearchFilter,
        @PageableDefault(size = 10, sort = "startReservation", direction = Sort.Direction.ASC) Pageable pageable
    ){

        return  ResponseEntity
                .ok(reservationService.searchByFilter(reservationSearchFilter , pageable));
    }

}
