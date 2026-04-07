package red1xx8.reservationsystem.reservation.dto.response;

import java.util.List;

public record ReservationSliceDto(
        List<ReservationResponse> content,
        Integer numberPage,
        Integer sizePage,
        Boolean hasNext

) {
}
