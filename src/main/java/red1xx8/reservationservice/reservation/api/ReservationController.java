package red1xx8.reservationservice.reservation.api;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import red1xx8.reservationservice.auth.model.UserPrincipal;
import red1xx8.reservationservice.reservation.dto.request.ChangeStatusRequest;
import red1xx8.reservationservice.reservation.dto.request.ReservationRequest;
import red1xx8.reservationservice.reservation.dto.request.ReservationSearchFilter;
import red1xx8.reservationservice.reservation.dto.response.ReservationResponse;
import red1xx8.reservationservice.reservation.dto.response.ReservationSliceDto;
import red1xx8.reservationservice.reservation.repository.ReservationRepository;
import red1xx8.reservationservice.reservation.service.ReservationService;


@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;

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
    @PreAuthorize("#userId == authentication.principal.id or hasAnyRole('ADMIN' , 'MAIN_ADMIN')")
    public ResponseEntity<ReservationResponse> updateReservation(
            @RequestBody @Valid ReservationRequest reservationRequest,
            @PathVariable  Long id
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
