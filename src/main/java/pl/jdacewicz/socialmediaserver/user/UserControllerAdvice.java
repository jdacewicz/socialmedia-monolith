package pl.jdacewicz.socialmediaserver.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.jdacewicz.socialmediaserver.ApiError;

@RestControllerAdvice
class UserControllerAdvice {

    @ExceptionHandler(UsernameNotFoundException.class)
    ResponseEntity<ApiError> handleUserNotFoundException(UsernameNotFoundException exception) {
        var status = HttpStatus.NOT_FOUND;
        var error = new ApiError(status.value(), exception.getMessage());
        return ResponseEntity.status(status)
                .body(error);
    }

    @ExceptionHandler(UserNotSignedInException.class)
    ResponseEntity<ApiError> handleUserNotSignedInException(UserNotSignedInException exception) {
        var status = HttpStatus.NOT_FOUND;
        var error = new ApiError(status.value(), exception.getMessage());
        return ResponseEntity.status(status)
                .body(error);
    }
}
