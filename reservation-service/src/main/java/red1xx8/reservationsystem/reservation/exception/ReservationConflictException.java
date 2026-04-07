package red1xx8.reservationsystem.reservation.exception;

public class ReservationConflictException extends  RuntimeException{
    public ReservationConflictException(String message){
        super(message);
    }
}
