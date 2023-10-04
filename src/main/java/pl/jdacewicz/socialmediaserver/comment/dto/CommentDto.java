package pl.jdacewicz.socialmediaserver.comment.dto;

import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

import java.time.LocalDateTime;

public record CommentDto(long id,
                         LocalDateTime creationDateTime,
                         String content,
                         String imageUrl,
                         UserDto creator) {
}
