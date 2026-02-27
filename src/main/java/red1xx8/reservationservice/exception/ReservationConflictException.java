package red1xx8.reservationservice.exception;

public class ReservationConflictException extends  RuntimeException{
    public ReservationConflictException(String message){
        super(message);
    }
}
