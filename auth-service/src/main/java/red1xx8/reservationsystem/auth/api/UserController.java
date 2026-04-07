package red1xx8.reservationsystem.auth.api;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import red1xx8.reservationsystem.auth.dto.request.ChangeUserRequest;
import red1xx8.reservationsystem.auth.dto.request.UserSearchFilter;
import red1xx8.reservationsystem.auth.dto.response.AuthResponse;
import red1xx8.reservationsystem.auth.dto.response.UserSliceDto;
import red1xx8.reservationsystem.auth.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("/admin/searchByFilter")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MAIN_ADMIN')")
    public ResponseEntity<UserSliceDto> searchByFilter(
            @RequestBody UserSearchFilter filter ,
            @PageableDefault(size = 10 , sort = "userName" , direction = Sort.Direction.ASC) Pageable pageable
    ){
        return ResponseEntity
                .ok(userService.searchUsersByFilter(filter , pageable));
    }

    @PostMapping("/admin/{id}/blockUser")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MAIN_ADMIN')")
    public ResponseEntity<AuthResponse> blockUser(
            @PathVariable("id") Long id
    ){
        return ResponseEntity
                .ok(userService.blockUser(id));
    }

    @PostMapping("/admin/{id}/unBlockUser")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MAIN_ADMIN')")
    public ResponseEntity<AuthResponse> unBlockUser(
            @PathVariable("id") Long id
    ){
        return ResponseEntity
                .ok(userService.unBlockUser(id));
    }

    @PatchMapping("/changeDataUser/{id}")
    public ResponseEntity<AuthResponse> changeDataUser(
            @PathVariable("id") Long id,
            @RequestBody @Valid ChangeUserRequest request
    ){
        return ResponseEntity
                .ok(userService.changeDataUser(id , request));

    }



}
