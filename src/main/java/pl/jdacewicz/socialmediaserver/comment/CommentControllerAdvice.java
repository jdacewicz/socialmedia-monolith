package pl.jdacewicz.socialmediaserver.comment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.jdacewicz.socialmediaserver.ApiError;

import java.util.List;

@RestControllerAdvice
public class CommentControllerAdvice {

    @ExceptionHandler(CommentNotFoundException.class)
    ResponseEntity<ApiError> handleCommentNotFoundException(CommentNotFoundException exception) {
        var status = HttpStatus.NOT_FOUND;
        var error = new ApiError(status.value(), List.of(exception.getMessage()));
        return ResponseEntity.status(status)
                .body(error);
    }
}
