package red1xx8.reservationsystem.auth.dto.request;

import jakarta.validation.constraints.AssertTrue;

public record ChangeUserRequest(

        String userName,
        String oldPass,
        String newPass,
        String numberPhone
) {
    @AssertTrue(message = "To change the user information, you must enter a password")
    public Boolean dataProtection(){
        if(userName != null || newPass != null || numberPhone != null){
            return oldPass != null && !oldPass.isBlank();
        }
        return true;
    }
}
