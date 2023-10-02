package pl.jdacewicz.socialmediaserver.token;

import org.springframework.stereotype.Component;
import pl.jdacewicz.socialmediaserver.token.dto.TokenDto;
import pl.jdacewicz.socialmediaserver.token.dto.TokenRequest;

@Component
class TokenMapper {

    TokenDto toDto(Token token) {
        return new TokenDto(token.getCode(),
                token.isTokenValid());
    }

    Token toToken(TokenRequest request) {
        return new Token(request.jwtToken(),
                request.user());
    }
}
