package red1xx8.reservationservice.exception;


import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNotFoundException(EntityNotFoundException e){
        logger.info("handlerNotFoundException:{}" , e.getMessage());
        var error = new ErrorResponse("Not found error" , e.getMessage(), LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }



    @ExceptionHandler({
            IllegalArgumentException.class,
            IllegalStateException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<ErrorResponse> handlerBadRequestException(Exception e){
        logger.info("handlerBadRequestException:{}" , e.getMessage());
        var error = new ErrorResponse("Bad Request" , e.getMessage(), LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerGenericException(Exception e){
        logger.info("handlerGenericException:{}" , e.getMessage());
        var error = new ErrorResponse("internal server error" , e.getMessage(), LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    @ExceptionHandler(InvalidTransactionalStatusException.class)
    public ResponseEntity<ErrorResponse> handlerConflictTransactional(Exception e){
        logger.info("handlerConflictTransactional:{}" , e.getMessage());
        var error = new ErrorResponse(
                "Conflict transactional error",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }
}
