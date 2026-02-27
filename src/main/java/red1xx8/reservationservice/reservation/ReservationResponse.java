package red1xx8.reservationservice.reservation;

import java.time.LocalDateTime;

public record ReservationResponse(
        Long id,
        String numberTable,
        String status,
        LocalDateTime startReservation,
        LocalDateTime endReservation
) {
}
