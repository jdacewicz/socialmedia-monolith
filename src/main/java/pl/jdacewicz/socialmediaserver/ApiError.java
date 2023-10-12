package pl.jdacewicz.socialmediaserver;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ApiError(int code,

                       @NotNull
                       List<String> messages) {
}
