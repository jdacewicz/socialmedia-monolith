package pl.jdacewicz.socialmediaserver.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.jdacewicz.socialmediaserver.comment.Comment;
import pl.jdacewicz.socialmediaserver.post.Post;
import pl.jdacewicz.socialmediaserver.reaction.ReactionUser;
import pl.jdacewicz.socialmediaserver.token.Token;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "t_users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter(value = AccessLevel.PACKAGE)
public class User implements UserDetails {

    final static String USERS_STORE_DIRECTORY_URL = "data/users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    private String profilePictureName;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @OneToMany(mappedBy = "creator")
    private List<Post> posts;

    @OneToMany(mappedBy = "creator")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<ReactionUser> reactionUsers;

    public String getDirectoryUrl() {
        return USERS_STORE_DIRECTORY_URL + "/" + this.id;
    }

    public String getProfilePictureUrl() {
        return getDirectoryUrl() + "/" + this.profilePictureName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
