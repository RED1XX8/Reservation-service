package red1xx8.reservationsystem.reservation.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import red1xx8.reservationsystem.reservation.model.Event;

import java.time.LocalDateTime;

public record ReservationRequest(

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
