package red1xx8.reservationservice.reservation.dto.request;

import red1xx8.reservationservice.reservation.model.ReservationStatus;

public record ChangeStatusRequest(
        ReservationStatus status
) {
}
