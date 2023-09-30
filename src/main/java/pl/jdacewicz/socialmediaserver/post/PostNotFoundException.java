package pl.jdacewicz.socialmediaserver.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class PostNotFoundException extends RuntimeException {

    PostNotFoundException(String message) {
        super(message);
    }
}
