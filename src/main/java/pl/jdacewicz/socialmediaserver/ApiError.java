package pl.jdacewicz.socialmediaserver;

public record ApiError(int code,
                       String message) {
}
