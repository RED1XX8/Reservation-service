package red1xx8.reservationsystem.auth.api;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import red1xx8.reservationsystem.auth.dto.request.AuthRequest;
import red1xx8.reservationsystem.auth.dto.request.LoginRequest;
import red1xx8.reservationsystem.auth.dto.response.AuthResponse;
import red1xx8.reservationsystem.auth.service.AuthService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class AuthController {

    private final AuthService authService;




    @PostMapping("/registration")
    public ResponseEntity<AuthResponse> registrationUser(
            @RequestBody @Valid AuthRequest request
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.registrationUser(request));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid LoginRequest request
    ){
        return ResponseEntity
                .ok(authService.login(request));
    }


}
