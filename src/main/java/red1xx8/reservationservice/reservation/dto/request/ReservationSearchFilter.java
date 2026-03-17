package red1xx8.reservationservice.reservation.dto.request;

import red1xx8.reservationservice.reservation.model.Event;
import red1xx8.reservationservice.reservation.model.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationSearchFilter(
        Long id,
        Long userId,
        Long tableId,
        LocalDateTime startReservation,
        LocalDateTime endReservation,
        Event event,
        ReservationStatus status
) {
}
