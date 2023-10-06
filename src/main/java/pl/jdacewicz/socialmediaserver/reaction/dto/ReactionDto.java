package pl.jdacewicz.socialmediaserver.reaction.dto;

public record ReactionDto(long id,
                          String name,
                          String imageUrl,
                          int count) {
}
