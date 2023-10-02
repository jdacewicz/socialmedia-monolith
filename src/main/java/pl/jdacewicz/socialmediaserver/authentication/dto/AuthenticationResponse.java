package pl.jdacewicz.socialmediaserver.authentication.dto;

public record AuthenticationResponse(String accessToken,
                                     String refreshToken) {
}
