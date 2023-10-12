package pl.jdacewicz.socialmediaserver.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(@Min(1)
                      long id,

                      @NotBlank
                      @Email
                      @Size(min = 5, max = 32)
                      String email,

                      @NotBlank
                      @Size(min = 2, max = 16)
                      String firstname,

                      @NotBlank
                      @Size(min = 2, max = 22)
                      String lastname,

                      String profilePictureUrl) {
}
