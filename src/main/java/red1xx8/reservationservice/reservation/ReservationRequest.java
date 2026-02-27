package red1xx8.reservationservice.reservation;

import jakarta.persistence.Id;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservationRequest(
    @NotNull
    Long userId,
    @NotNull
    Long tableId,
    @NotNull
    Integer capacity,
    @NotNull
    Event event,
    @NotNull
    @FutureOrPresent
    LocalDateTime startReservation,
    @NotNull
    @FutureOrPresent
    LocalDateTime endReservation,
    @NotNull
    String comment
) {
    @AssertTrue(message = "Start reservation must be before end reservation")
    public Boolean isValidTimeReservation(){
        return startReservation.isBefore(endReservation);
    }
}
