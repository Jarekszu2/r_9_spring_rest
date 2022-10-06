package jarek.rest.controller;

import jarek.rest.exception.WrongOperation;
import jarek.rest.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleEntityNotFound(EntityNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) //404
                .body(new ErrorMessage("Unable to find entity " + exception.getMessage()    ));
    }

    @ExceptionHandler(value = {WrongOperation.class})
    public ResponseEntity<ErrorMessage> handleWrongOperation(WrongOperation exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400
                .body(new ErrorMessage(exception.getMessage()));
    }
}
