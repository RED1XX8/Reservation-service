package red1xx8.reservationservice.auth.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import red1xx8.reservationservice.auth.dto.request.AuthRequest;
import red1xx8.reservationservice.auth.dto.response.AuthResponse;
import red1xx8.reservationservice.auth.repository.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "role" , ignore = true)
    @Mapping(target = "status" , ignore = true)
    @Mapping(target = "password", ignore = true)
    public UserEntity toEntity(AuthRequest authRequest);


    @Mapping(target = "token" , ignore = true)
    public AuthResponse toResponse(UserEntity entity);
}
