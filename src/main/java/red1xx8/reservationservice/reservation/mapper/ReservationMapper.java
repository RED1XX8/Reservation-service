package red1xx8.reservationservice.reservation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import red1xx8.reservationservice.reservation.repository.ReservationEntity;
import red1xx8.reservationservice.reservation.dto.request.ReservationRequest;
import red1xx8.reservationservice.reservation.dto.response.ReservationResponse;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "userId" , ignore = true)
    @Mapping(target = "table" , ignore = true)
    @Mapping(target = "status", ignore = true)
    public ReservationEntity toEntity(ReservationRequest request);

    @Mapping(source = "table.numberTable" , target = "numberTable")
    public ReservationResponse toResponse(ReservationEntity entity);


}
