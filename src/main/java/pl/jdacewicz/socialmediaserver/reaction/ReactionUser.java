package pl.jdacewicz.socialmediaserver.reaction;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.jdacewicz.socialmediaserver.comment.Comment;
import pl.jdacewicz.socialmediaserver.post.Post;
import pl.jdacewicz.socialmediaserver.user.User;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "t_reactions_users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(value = AccessLevel.PACKAGE)
public class ReactionUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reaction_id")
    private Reaction reaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "reactionUsers")
    private List<Post> posts = new LinkedList<>();

    @ManyToMany(mappedBy = "reactionUsers")
    private List<Comment> comments = new LinkedList<>();

    public ReactionUser(Reaction reaction, User user) {
        this.reaction = reaction;
        this.user = user;
    }

    public boolean isUserUnique(List<ReactionUser> reactionUsers) {
        return reactionUsers.stream()
                .anyMatch(r -> r.getUser()
                        .equals(this.user));
    }
}
