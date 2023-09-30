package pl.jdacewicz.socialmediaserver.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
class Post {

    final static String POSTS_STORE_DIRECTORY_URL = "data/posts";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime creationDateTime = LocalDateTime.now();

    private String content;

    private String image;

    private boolean visible = true;

    Post(String content, String image) {
        this.content = content;
        this.image = image;
    }

    String getDirectoryUrl() {
        return POSTS_STORE_DIRECTORY_URL + "/" + this.id;
    }

    String getImageUrl() {
        return getDirectoryUrl() + "/" + this.image;
    }
}
