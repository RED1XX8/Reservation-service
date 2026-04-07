package red1xx8.reservationsystem.reservation.dto.response;

import java.time.LocalDateTime;

public record ReservationResponse(
        Long id,
        String numberTable,
        String status,
        LocalDateTime startReservation,
        LocalDateTime endReservation
) {
}
