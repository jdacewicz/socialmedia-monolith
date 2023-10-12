package pl.jdacewicz.socialmediaserver.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationResponse(@NotBlank
                                     @Size(max = 180)
                                     String accessToken,

                                     @NotBlank
                                     @Size(max = 180)
                                     String refreshToken) {
}
