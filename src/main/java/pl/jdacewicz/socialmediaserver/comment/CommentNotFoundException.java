package pl.jdacewicz.socialmediaserver.comment;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(String message) {
        super(message);
    }
}
