package pl.jdacewicz.socialmediaserver.reaction.dto;

import jakarta.validation.constraints.NotNull;

public record ReactionCounter(@NotNull
                              ReactionDto reaction,

                              long count) {
}
