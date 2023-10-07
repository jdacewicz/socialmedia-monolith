package pl.jdacewicz.socialmediaserver.post.dto;

import pl.jdacewicz.socialmediaserver.comment.dto.CommentDto;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionCounter;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;

public record PostDto(long id,
                      LocalDateTime creationDateTime,
                      String content,
                      String imageUrl,
                      UserDto creator,
                      List<CommentDto> comments,
                      List<ReactionCounter> reactionCounters) {
}
