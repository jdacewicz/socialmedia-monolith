package pl.jdacewicz.socialmediaserver.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.jdacewicz.socialmediaserver.Image;
import pl.jdacewicz.socialmediaserver.comment.Comment;
import pl.jdacewicz.socialmediaserver.reaction.ReactionUser;
import pl.jdacewicz.socialmediaserver.user.User;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "t_posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(value = AccessLevel.PACKAGE)
public class Post implements Image {

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

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new LinkedList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<ReactionUser> reactionUsers = new LinkedList<>();

    public Post(String content, String imageName, User creator) {
        this.content = content;
        this.imageName = imageName;
        this.creator = creator;
    }

    public Post(long id, LocalDateTime creationDateTime, String content, User creator) {
        this.id = id;
        this.creationDateTime = creationDateTime;
        this.content = content;
        this.creator = creator;
    }

    @Override
    public String getImageUrl() {
        return getDirectoryUrl() + "/" + this.imageName;
    }

    @Override
    public String getDirectoryUrl() {
        return MAIN_POSTS_DIRECTORY_URL + "/" + this.id;
    }

    public void addReactionUser(ReactionUser reactionUser) {
        if (reactionUser.isUserNotUnique(getReactionUsers())) {
            throw new UnsupportedOperationException();
        }
        this.reactionUsers.add(reactionUser);
        reactionUser.getPosts().add(this);
    }
}
