package pl.phanta.springnativegradle.infrastructure.web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.phanta.springnativegradle.application.exceptions.AccessDeniedException;
import pl.phanta.springnativegradle.application.exceptions.ResourceNotFoundException;
import pl.phanta.springnativegradle.model.Error;

@RestControllerAdvice
class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<Error> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new Error().message(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    private ResponseEntity<Error> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(new Error().message(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

}
