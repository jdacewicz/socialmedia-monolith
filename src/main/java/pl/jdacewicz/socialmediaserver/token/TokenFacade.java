package pl.jdacewicz.socialmediaserver.token;

public interface TokenFacade {

    Token getTokenByCode(String code);

    void revokeAllUserTokens(long id);

    void saveToken(Token token);
}
