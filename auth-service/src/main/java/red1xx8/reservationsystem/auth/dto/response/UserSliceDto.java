package red1xx8.reservationsystem.auth.dto.response;

import java.util.List;

public record UserSliceDto(
        List<AuthResponse> content,
        Integer numberPage,
        Integer sizePage,
        Boolean hasNext
) {
}
