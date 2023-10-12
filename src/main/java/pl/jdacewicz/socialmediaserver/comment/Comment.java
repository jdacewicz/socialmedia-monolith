package pl.jdacewicz.socialmediaserver.comment;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.jdacewicz.socialmediaserver.Image;
import pl.jdacewicz.socialmediaserver.post.Post;
import pl.jdacewicz.socialmediaserver.reaction.ReactionUser;
import pl.jdacewicz.socialmediaserver.user.User;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "t_comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(value = AccessLevel.PACKAGE)
public class Comment implements Image {

    final static String MAIN_COMMENTS_DIRECTORY_URL = "comments";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private LocalDateTime creationDateTime = LocalDateTime.now();

    @NotNull
    @Size(max = 255)
    private String content;

    private String imageName;

    private boolean visible = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @NotNull
    private Post post;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ReactionUser> reactionUsers = new LinkedList<>();

    public Comment(String content, String imageName, User creator, Post post) {
        this.content = content;
        this.imageName = imageName;
        this.creator = creator;
        this.post = post;
    }

    @Override
    public String getImageUrl() {
        return getDirectoryUrl() + "/" + this.imageName;
    }

    @Override
    public String getDirectoryUrl() {
        return creator.getDirectoryUrl() + "/" + MAIN_COMMENTS_DIRECTORY_URL + "/" + this.id;
    }

    public void addReactionUser(ReactionUser reactionUser) {
        if (reactionUser.isUserNotUnique(getReactionUsers())) {
            throw new UnsupportedOperationException();
        }
        this.reactionUsers.add(reactionUser);
        reactionUser.getComments().add(this);
    }
}
