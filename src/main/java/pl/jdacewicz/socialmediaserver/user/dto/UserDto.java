package pl.jdacewicz.socialmediaserver.user.dto;

public record UserDto(long id,
                      String email,
                      String firstname,
                      String lastname,
                      String profilePictureUrl) {
}
