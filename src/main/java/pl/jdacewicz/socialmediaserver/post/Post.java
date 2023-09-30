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
public class Post {

    public final static String POSTS_STORE_DIRECTORY_URL = "";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime creationDateTime = LocalDateTime.now();

    private String content;

    private String image;

    private boolean visible = true;

    public Post(String content, String image) {
        this.content = content;
        this.image = image;
    }

    public String getDirectoryUrl() {
        return POSTS_STORE_DIRECTORY_URL + "/" + this.id;
    }

    public String getImageUrl() {
        return getDirectoryUrl() + "/" + this.image;
    }
}
