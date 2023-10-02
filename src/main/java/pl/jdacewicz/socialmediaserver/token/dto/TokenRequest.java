package pl.jdacewicz.socialmediaserver.token.dto;

import pl.jdacewicz.socialmediaserver.user.User;

public record TokenRequest(User user,
                           String jwtToken) {
}
