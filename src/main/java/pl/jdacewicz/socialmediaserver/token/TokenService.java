package pl.jdacewicz.socialmediaserver.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TokenService {

    private final TokenRepository tokenRepository;

    Token getTokenByCode(String code) {
        return tokenRepository.findByCode(code)
                .orElseThrow(() -> new UnsupportedOperationException(""));
    }

    Token saveToken(Token token) {
        return tokenRepository.save(token);
    }
}
