package red1xx8.reservationservice.exception;

public class InvalidTransactionalStatusException extends RuntimeException{
    public InvalidTransactionalStatusException(String message){
        super(message);
    }
}
