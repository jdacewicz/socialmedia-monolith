package pl.jdacewicz.socialmediaserver.post.dto;

import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

import java.time.LocalDateTime;

public record PostDto(long id,
                      LocalDateTime creationDateTime,
                      String content,
                      String imageUrl,
                      UserDto creator) {
}
