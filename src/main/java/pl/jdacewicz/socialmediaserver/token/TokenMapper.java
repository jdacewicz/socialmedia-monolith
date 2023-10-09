package pl.jdacewicz.socialmediaserver.token;

import pl.jdacewicz.socialmediaserver.token.dto.TokenDto;

interface TokenMapper {

    TokenDto mapToDto(Token token);
}
