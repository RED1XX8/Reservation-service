package red1xx8.reservationsystem.auth.dto.request;

import jakarta.validation.constraints.NotNull;

public record AuthRequest(
        @NotNull
        String userName,
        @NotNull
        String numberPhone,
        @NotNull
        String password
) {
}
