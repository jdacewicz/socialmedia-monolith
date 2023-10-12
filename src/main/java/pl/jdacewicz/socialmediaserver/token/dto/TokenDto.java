package pl.jdacewicz.socialmediaserver.token.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TokenDto(@NotBlank
                       @Size(max = 180)
                       String code,

                       @NotBlank
                       @Size(max = 22)
                       String type,

                       boolean isActive) {
}
