package red1xx8.reservationservice.reservation;


import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;

    @PostMapping
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
    public Slice<ReservationResponse> getReservationByFilter(
        @RequestBody ReservationSearchFilter reservationSearchFilter,
        @PageableDefault(size = 20, sort = "startReservation", direction = Sort.Direction.ASC) Pageable pageable
    ){

        return  reservationService.searchByFilter(reservationSearchFilter , pageable);
    }

}
