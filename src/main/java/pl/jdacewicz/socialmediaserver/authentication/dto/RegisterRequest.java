package pl.jdacewicz.socialmediaserver.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@NotBlank
                              @Email
                              @Size(min = 5, max = 32)
                              String email,

                              @NotBlank
                              @Size(min = 8, max = 20)
                              String password,

                              @NotBlank
                              @Size(min = 2, max = 16)
                              String firstname,

                              @NotBlank
                              @Size(min = 2, max = 22)
                              String lastname) {
}
