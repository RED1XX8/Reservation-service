package red1xx8.reservationsystem.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @NotBlank
        String login,
        @NotBlank
        String password
) {
}
