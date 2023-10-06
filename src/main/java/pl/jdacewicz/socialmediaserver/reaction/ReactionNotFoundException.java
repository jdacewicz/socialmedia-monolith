package pl.jdacewicz.socialmediaserver.reaction;

class ReactionNotFoundException extends RuntimeException {

    public ReactionNotFoundException(String message) {
        super(message);
    }
}
