package pl.jdacewicz.socialmediaserver.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_posts")
@NoArgsConstructor
@Getter
@Setter
class Post {

    private final static String POSTS_STORE_DIRECTORY_URL = "";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime creationDateTime = LocalDateTime.now();

    private String content;

    private boolean visible = true;

    public static String getDirectoryUrl(long id) {
        return POSTS_STORE_DIRECTORY_URL + "/" + id;
    }
}
