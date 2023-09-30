package pl.jdacewicz.socialmediaserver.post;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.jdacewicz.socialmediaserver.ApiError;

@RestControllerAdvice
class PostControllerAdvice {

    @ExceptionHandler(PostNotFoundException.class)
    ResponseEntity<ApiError> handlePostNotFoundException(PostNotFoundException exception) {
        var status = HttpStatus.NOT_FOUND;
        var error = new ApiError(status.value(), exception.getMessage());
        return ResponseEntity.status(status)
                .body(error);
    }
}
