package pl.jdacewicz.socialmediaserver.authentication.dto;

public record RegisterRequest(String email,
                              String password,
                              String firstname,
                              String lastname) {
}
