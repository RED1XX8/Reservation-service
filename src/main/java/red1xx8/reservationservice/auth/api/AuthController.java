package red1xx8.reservationservice.auth.api;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import red1xx8.reservationservice.auth.dto.request.AuthRequest;
import red1xx8.reservationservice.auth.dto.request.LoginRequest;
import red1xx8.reservationservice.auth.dto.response.AuthResponse;
import red1xx8.reservationservice.auth.service.AuthService;
import red1xx8.reservationservice.auth.repository.UserRepository;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;




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
