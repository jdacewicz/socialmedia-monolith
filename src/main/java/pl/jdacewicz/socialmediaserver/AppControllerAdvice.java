package pl.jdacewicz.socialmediaserver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedList;
import java.util.List;

@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handlePostNotFoundException(MethodArgumentNotValidException exception) {
        List<String> errors = new LinkedList<>();
        exception.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    if (error instanceof FieldError fieldError) {
                        errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
                    }
                });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(HttpStatus.BAD_REQUEST.value(), errors));
    }
}
