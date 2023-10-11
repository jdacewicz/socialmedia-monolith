package pl.jdacewicz.socialmediaserver.reaction.dto;

public record ReactionCounter(ReactionDto reaction,
                              long count) {
}
