package pl.jdacewicz.socialmediaserver.comment.dto;

import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionCounter;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;

public record CommentDto(long id,
                         LocalDateTime creationDateTime,
                         String content,
                         String imageUrl,
                         String directoryUrl,
                         UserDto creator,
                         List<ReactionCounter> reactionCounters) {
}
