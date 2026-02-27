package red1xx8.reservationservice.reservation;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "user" , ignore = true)
    @Mapping(target = "table" , ignore = true)
    @Mapping(target = "status", ignore = true)
    public ReservationEntity toEntity(ReservationRequest request);

    @Mapping(source = "table.numberTable" , target = "numberTable")
    public ReservationResponse toResponse(ReservationEntity entity);
}
