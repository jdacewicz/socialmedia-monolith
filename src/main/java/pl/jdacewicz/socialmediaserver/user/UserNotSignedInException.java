package pl.jdacewicz.socialmediaserver.user;

public class UserNotSignedInException extends RuntimeException {

    public UserNotSignedInException(String message) {
        super(message);
    }
}
