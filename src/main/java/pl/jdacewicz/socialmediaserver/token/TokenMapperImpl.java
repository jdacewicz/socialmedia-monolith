package pl.jdacewicz.socialmediaserver.token;

import org.springframework.stereotype.Component;
import pl.jdacewicz.socialmediaserver.token.dto.TokenDto;

@Component
class TokenMapperImpl implements TokenMapper {

    @Override
    public TokenDto mapToDto(Token token) {
        return new TokenDto(token.getCode(),
                token.getTokenType().toString(),
                token.isTokenActive());
    }
}
