package red1xx8.reservationservice.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        String detailedMessage,
        LocalDateTime timeException
) {
}
