package pl.jdacewicz.socialmediaserver.post;

class PostNotFoundException extends RuntimeException {

    PostNotFoundException(String message) {
        super(message);
    }
}
