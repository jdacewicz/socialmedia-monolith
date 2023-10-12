package pl.jdacewicz.socialmediaserver.reaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ReactionDto(int id,

                          @NotBlank
                          @Size(min = 2, max = 50)
                          String name,

                          @NotBlank
                          String imageUrl,

                          @NotBlank
                          String directoryUrl) {
}
