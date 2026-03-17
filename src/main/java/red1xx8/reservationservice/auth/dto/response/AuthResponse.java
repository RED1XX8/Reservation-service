package red1xx8.reservationservice.auth.dto.response;

import red1xx8.reservationservice.auth.model.Roles;
import red1xx8.reservationservice.auth.model.UserStatus;

public record AuthResponse(
        Long id,
        String userName,
        String numberPhone,
        Roles role,
        UserStatus status,
        String token
) {
}
