package pl.jdacewicz.socialmediaserver.token;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.jdacewicz.socialmediaserver.token.dto.TokenDto;
import pl.jdacewicz.socialmediaserver.user.UserMapper;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

@Service
@RequiredArgsConstructor
class TokenService implements TokenFacade {

    @Value("${message.token.not-found}")
    private String notFoundTokenMessage;

    private final TokenRepository tokenRepository;
    private final TokenMapper tokenMapper;
    private final UserMapper userMapper;

    @Override
    public TokenDto getTokenByCode(String code) {
        return tokenRepository.findByCode(code)
                .map(tokenMapper::mapToDto)
                .orElseThrow(() -> new TokenNotFoundException(notFoundTokenMessage));
    }

    @Override
    public void revokeAllUserTokens(long id) {
        var tokens = tokenRepository
                .findAllValidTokenByUser(id)
                .stream()
                .peek(token -> {
                    token.setExpired(true);
                    token.setRevoked(true);
                })
                .toList();
        tokenRepository.saveAll(tokens);
    }

    @Override
    public void saveToken(String jwtToken, UserDto userDto) {
        var user = userMapper.mapToEntity(userDto);
        var token = new Token(jwtToken, user);
        tokenRepository.save(token);
    }
}
