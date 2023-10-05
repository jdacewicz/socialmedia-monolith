package pl.jdacewicz.socialmediaserver.comment;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.jdacewicz.socialmediaserver.post.Post;
import pl.jdacewicz.socialmediaserver.reaction.Reaction;
import pl.jdacewicz.socialmediaserver.user.User;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "t_comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(value = AccessLevel.PACKAGE)
public class Comment {

    final static String MAIN_COMMENTS_DIRECTORY_URL = "comments";

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToMany
    private List<Reaction> reactions = new LinkedList<>();

    public Comment(String content, String imageName, User creator, Post post) {
        this.content = content;
        this.imageName = imageName;
        this.creator = creator;
        this.post = post;
    }

    public String getImageUrl() {
        return getDirectoryUrl() + "/" + this.imageName;
    }

    public String getDirectoryUrl() {
        return creator.getDirectoryUrl() + "/" + MAIN_COMMENTS_DIRECTORY_URL + "/" + this.id;
    }
}
