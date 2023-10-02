package pl.jdacewicz.socialmediaserver.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jdacewicz.socialmediaserver.token.dto.TokenDto;
import pl.jdacewicz.socialmediaserver.token.dto.TokenRequest;

@Component
@RequiredArgsConstructor
public class TokenFacade {

    private final TokenService tokenService;
    private final TokenMapper tokenMapper;

    public TokenDto getTokenByCode(String code) {
        var foundToken = tokenService.getTokenByCode(code);
        return tokenMapper.toDto(foundToken);
    }

    public TokenDto saveToken(TokenRequest request) {
        var token = tokenMapper.toToken(request);
        var savedToken = tokenService.saveToken(token);
        return tokenMapper.toDto(savedToken);
    }
}
