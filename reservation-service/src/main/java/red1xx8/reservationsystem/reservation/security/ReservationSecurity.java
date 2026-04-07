package red1xx8.reservationsystem.reservation.security;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import red1xx8.reservationsystem.reservation.repository.ReservationRepository;

@Component("reservationSecurity")
@RequiredArgsConstructor
public class ReservationSecurity {

    private final ReservationRepository repository;

    public boolean canUpdate(Long reservationId , Long userId){
        return repository.findById(reservationId)
                .map(reservation ->
                        reservation.getUserId().equals(userId))
                .orElse(false);
    }


}
