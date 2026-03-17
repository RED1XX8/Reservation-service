package red1xx8.reservationservice.auth.dto.response;

import java.util.List;

public record UserSliceDto(
        List<AuthResponse> content,
        Integer numberPage,
        Integer sizePage,
        Boolean hasNext
) {
}
