package pl.jdacewicz.socialmediaserver.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
import java.util.Objects;

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

    @Column(unique = true)
    @NotBlank
    @Email
    @Size(min = 5, max = 32)
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    private String password;

    @NotBlank
    @Size(min = 2, max = 16)
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 22)
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

    public User(long id, String email, String firstname, String lastname) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

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
