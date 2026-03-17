package red1xx8.reservationservice.auth.dto.request;

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
