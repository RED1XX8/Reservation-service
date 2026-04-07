package red1xx8.reservationsystem.auth.dto.request;

import red1xx8.reservationsystem.auth.model.Roles;
import red1xx8.reservationsystem.auth.model.UserStatus;

public record UserSearchFilter(
        Long id ,
        String userName,
        String numberPhone,
        Roles role,
        UserStatus status
) {
}
