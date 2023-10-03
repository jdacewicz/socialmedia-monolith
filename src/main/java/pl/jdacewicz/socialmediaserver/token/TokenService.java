package pl.jdacewicz.socialmediaserver.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TokenService implements TokenFacade{

    private final TokenRepository tokenRepository;

    @Override
    public Token getTokenByCode(String code) {
        return tokenRepository.findByCode(code)
                .orElseThrow(() -> new UnsupportedOperationException(""));
    }

    @Override
    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }
}
