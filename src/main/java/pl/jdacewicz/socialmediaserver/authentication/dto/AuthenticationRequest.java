package pl.jdacewicz.socialmediaserver.authentication.dto;

public record AuthenticationRequest(String email,
                                    String password) {
}
