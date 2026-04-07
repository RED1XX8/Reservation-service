package red1xx8.reservationsystem.reservation.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        String detailedMessage,
        LocalDateTime timeException
) {
}
