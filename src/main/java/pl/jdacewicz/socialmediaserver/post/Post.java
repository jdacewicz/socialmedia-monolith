package pl.jdacewicz.socialmediaserver.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.jdacewicz.socialmediaserver.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Post {

    final static String MAIN_POSTS_DIRECTORY_URL = "data/posts";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime creationDateTime = LocalDateTime.now();

    private String content;

    private String imageName;

    private boolean visible = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User creator;

    public Post(String content, String imageName, User creator) {
        this.content = content;
        this.imageName = imageName;
        this.creator = creator;
    }

    public String getImageUrl() {
        return getDirectoryUrl() + "/" + this.imageName;
    }

    public String getDirectoryUrl() {
        return MAIN_POSTS_DIRECTORY_URL + "/" + this.id;
    }
}
