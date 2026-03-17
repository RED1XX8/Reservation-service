package red1xx8.reservationservice.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @NotBlank
        String login,
        @NotBlank
        String password
) {
}
