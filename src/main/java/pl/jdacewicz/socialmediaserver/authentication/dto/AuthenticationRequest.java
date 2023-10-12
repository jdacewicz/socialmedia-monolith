package pl.jdacewicz.socialmediaserver.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest(@NotBlank
                                    @Email
                                    @Size(min = 5, max = 32)
                                    String email,

                                    @NotBlank
                                    @Size(min = 8, max = 20)
                                    String password) {
}
