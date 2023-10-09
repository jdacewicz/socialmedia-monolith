package pl.jdacewicz.socialmediaserver.reaction.dto;

public record ReactionDto(int id,
                          String name,
                          String imageUrl,
                          String directoryUrl) {
}
