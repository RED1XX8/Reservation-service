package red1xx8.reservationsystem.reservation.dto.request;

import red1xx8.reservationsystem.reservation.model.ReservationStatus;

public record ChangeStatusRequest(
        ReservationStatus status
) {
}
