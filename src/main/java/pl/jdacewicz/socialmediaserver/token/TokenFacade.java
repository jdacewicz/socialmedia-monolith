package pl.jdacewicz.socialmediaserver.token;

import pl.jdacewicz.socialmediaserver.token.dto.TokenDto;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

public interface TokenFacade {

    TokenDto getTokenByCode(String code);

    void revokeAllUserTokens(long id);

    void saveToken(String jwtToken, UserDto userDto);
}
