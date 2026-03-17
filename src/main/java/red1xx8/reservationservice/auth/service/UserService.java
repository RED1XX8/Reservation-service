package red1xx8.reservationservice.auth.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red1xx8.reservationservice.auth.dto.request.AuthRequest;
import red1xx8.reservationservice.auth.dto.request.ChangeUserRequest;
import red1xx8.reservationservice.auth.dto.response.AuthResponse;
import red1xx8.reservationservice.auth.dto.request.UserSearchFilter;
import red1xx8.reservationservice.auth.dto.response.UserSliceDto;
import red1xx8.reservationservice.auth.mapper.UserMapper;
import red1xx8.reservationservice.auth.model.UserPrincipal;
import red1xx8.reservationservice.auth.model.UserSpec;
import red1xx8.reservationservice.auth.model.UserStatus;
import red1xx8.reservationservice.auth.repository.UserEntity;
import red1xx8.reservationservice.auth.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public UserSliceDto searchUsersByFilter(
            UserSearchFilter filter ,
            Pageable pageable
            ){

        var spec = Specification.where(
                UserSpec.hasId(filter.id())
                        .and(UserSpec.hasUserName(filter.userName()))
                        .and(UserSpec.hasNumberPhone(filter.numberPhone()))
                        .and(UserSpec.hasRole(filter.role()))
                        .and(UserSpec.hasStatus(filter.status()))
        );


        var page = repository.findAll(spec , pageable);

        var content = page.getContent()
                .stream()
                .map(mapper :: toResponse)
                .toList();

        var hasNext = page.hasNext();

        SliceImpl<AuthResponse> slice = new SliceImpl<>(content, pageable ,hasNext);


        logger.info("Found {} users by filter" , content.size());

        return new UserSliceDto(
                slice.getContent(),
                slice.getNumber(),
                slice.getSize(),
                slice.hasNext()
        );

    }


    @Transactional
    public  AuthResponse blockUser(Long id) {

        var entityToBlock = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User is not found by id:" + id));

        entityToBlock.setStatus(UserStatus.BLOCKED);

        return mapper.toResponse(entityToBlock);
    }

    @Transactional
    public  AuthResponse unBlockUser(Long id) {
        var entityToBlock = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User is not found by id:" + id));

        entityToBlock.setStatus(UserStatus.ACTIVE);
        return mapper.toResponse(entityToBlock);

    }

    @Transactional
    public  AuthResponse changeDataUser(Long id,  ChangeUserRequest request) {

        UserEntity userToChange = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User is not found by id:" + id));

        if(!passwordEncoder.matches(request.oldPass(), userToChange.getPassword())){
            throw new IllegalArgumentException("Password don't matched");
        }

        if(request.userName() != null
                && !userToChange.getUserName().equals(request.userName())
                && repository.existsByUserName(request.userName())
                &&  !request.userName().isBlank()){
            throw new IllegalArgumentException("This userName already is busy");
        }
        userToChange.setUserName(request.userName());

        if(request.newPass() != null && !request.newPass().isBlank()){
            userToChange.setPassword(passwordEncoder.encode(request.newPass()));
        }
        if(request.numberPhone() != null && !request.numberPhone().isBlank()){
            userToChange.setNumberPhone(request.numberPhone());
        }


        UserPrincipal user = new UserPrincipal(userToChange);

        return new AuthResponse(
                userToChange.getId(),
                userToChange.getUserName(),
                userToChange.getNumberPhone(),
                userToChange.getRole(),
                userToChange.getStatus(),
                jwtService.generateToken(user)
        );
    }
}
