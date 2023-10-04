package pl.jdacewicz.socialmediaserver.token;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.jdacewicz.socialmediaserver.user.User;

@Entity
@Table(name = "t_tokens")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
public class Token {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;

    private boolean revoked = false;

    private boolean expired = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Token(String code, User user) {
        this.code = code;
        this.user = user;
    }

    public boolean isTokenActive() {
        return (!revoked && !expired);
    }
}
