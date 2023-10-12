package pl.jdacewicz.socialmediaserver.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionCounter;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;

public record CommentDto(long id,

                         @NotNull
                         LocalDateTime creationDateTime,

                         @NotNull
                         @Size(max = 255)
                         String content,

                         @NotBlank
                         String imageUrl,

                         @NotBlank
                         String directoryUrl,

                         @NotNull
                         UserDto creator,

                         List<ReactionCounter> reactionCounters) {
}
