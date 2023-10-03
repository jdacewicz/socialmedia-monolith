package pl.jdacewicz.socialmediaserver.token;

public interface TokenFacade {

    Token getTokenByCode(String code);

    Token saveToken(Token token);
}
