package pl.jdacewicz.socialmediaserver.post.dto;

import java.time.LocalDateTime;

public record PostDto(long id,
                      LocalDateTime creationDateTime,
                      String content,
                      String imageUrl) {
}
