package pl.jdacewicz.socialmediaserver.token;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TokenService implements TokenFacade{

    @Value("${message.token.not-found}")
    private String notFoundTokenMessage;

    private final TokenRepository tokenRepository;

    @Override
    public Token getTokenByCode(String code) {
        return tokenRepository.findByCode(code)
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
    public void saveToken(Token token) {
        tokenRepository.save(token);
    }
}
