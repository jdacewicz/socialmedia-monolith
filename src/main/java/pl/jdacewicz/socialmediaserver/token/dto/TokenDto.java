package pl.jdacewicz.socialmediaserver.token.dto;

public record TokenDto(String code,
                       boolean valid) {
}
