package red1xx8.reservationservice.auth.dto.request;

import red1xx8.reservationservice.auth.model.Roles;
import red1xx8.reservationservice.auth.model.UserStatus;

public record UserSearchFilter(
        Long id ,
        String userName,
        String numberPhone,
        Roles role,
        UserStatus status
) {
}
