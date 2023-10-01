package pl.jdacewicz.socialmediaserver.token;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_tokens")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
class Token {

    @Id
    @GeneratedValue
    public long id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;
}
