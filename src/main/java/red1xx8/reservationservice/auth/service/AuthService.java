package red1xx8.reservationservice.auth.service;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red1xx8.reservationservice.auth.dto.request.AuthRequest;
import red1xx8.reservationservice.auth.dto.request.LoginRequest;
import red1xx8.reservationservice.auth.dto.response.AuthResponse;
import red1xx8.reservationservice.auth.mapper.UserMapper;
import red1xx8.reservationservice.auth.model.Roles;
import red1xx8.reservationservice.auth.model.UserPrincipal;
import red1xx8.reservationservice.auth.model.UserStatus;
import red1xx8.reservationservice.auth.repository.UserEntity;
import red1xx8.reservationservice.auth.repository.UserRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserMapper mapper;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);


    @Transactional
    public  AuthResponse registrationUser( AuthRequest request) {

        if(repository.existsByUserName(request.userName())){
            throw new IllegalArgumentException("User with that name has already been registered");
        }
        if(repository.existsByNumberPhone(request.numberPhone())){
            throw new IllegalArgumentException("User with that number phone has already been registered");
        }
        UserEntity entityToSave = mapper.toEntity(request);

        entityToSave.setPassword(passwordEncoder.encode(request.password()));
        entityToSave.setRole(Roles.GUEST);
        entityToSave.setStatus(UserStatus.ACTIVE);

        repository.save(entityToSave);

        logger.info("User with name {} is registered " , request.userName() );

        UserPrincipal principal = new UserPrincipal(entityToSave);

        return  new AuthResponse(
                entityToSave.getId(),
                entityToSave.getUserName(),
                entityToSave.getNumberPhone(),
                entityToSave.getRole(),
                entityToSave.getStatus(),
                jwtService.generateToken(principal)
        );
    }


    public  AuthResponse login( LoginRequest request) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.login(),
                        request.password()
                ));

        UserPrincipal userPrincipal = (UserPrincipal) Objects.requireNonNull(authentication.getPrincipal());

        return new AuthResponse(
                userPrincipal.getId(),
                userPrincipal.getUsername(),
                userPrincipal.getNumberPhone(),
                userPrincipal.getRole(),
                userPrincipal.getStatus(),
                jwtService.generateToken(userPrincipal)
        );
    }
}
