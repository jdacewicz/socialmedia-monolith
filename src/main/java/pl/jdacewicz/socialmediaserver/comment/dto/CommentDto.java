package pl.jdacewicz.socialmediaserver.comment.dto;

import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionDto;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;

public record CommentDto(long id,
                         LocalDateTime creationDateTime,
                         String content,
                         String imageUrl,
                         UserDto creator,
                         List<ReactionDto> reactions) {
}
