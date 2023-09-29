package pl.jdacewicz.socialmediaserver.post;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(String message) {
        super(message);
    }
}
