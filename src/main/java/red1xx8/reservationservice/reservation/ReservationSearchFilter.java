package red1xx8.reservationservice.reservation;

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
