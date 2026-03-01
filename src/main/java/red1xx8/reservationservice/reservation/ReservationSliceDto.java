package red1xx8.reservationservice.reservation;

import java.util.List;

public record ReservationSliceDto(
        List<ReservationResponse> content,
        Integer numberPage,
        Integer sizePage,
        Boolean hasNext

) {
}
