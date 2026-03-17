package red1xx8.reservationservice.reservation.exception;

public class ReservationConflictException extends  RuntimeException{
    public ReservationConflictException(String message){
        super(message);
    }
}
