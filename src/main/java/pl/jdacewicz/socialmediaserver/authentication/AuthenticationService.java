package pl.jdacewicz.socialmediaserver.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.jdacewicz.socialmediaserver.authentication.dto.AuthenticationRequest;
import pl.jdacewicz.socialmediaserver.authentication.dto.AuthenticationResponse;
import pl.jdacewicz.socialmediaserver.authentication.dto.RegisterRequest;
import pl.jdacewicz.socialmediaserver.token.Token;
import pl.jdacewicz.socialmediaserver.token.TokenFacade;
import pl.jdacewicz.socialmediaserver.user.User;
import pl.jdacewicz.socialmediaserver.user.UserDetailsFacade;

@Service
@RequiredArgsConstructor
class AuthenticationService implements AuthenticationFacade {

    private final UserDetailsFacade userDetailsFacade;
    private final TokenFacade tokenFacade;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
        var createdUser = userDetailsFacade.createUser(user);
        var jwtToken = jwtService.generateToken(createdUser);
        var refreshToken = jwtService.generateRefreshToken(createdUser);
        tokenFacade.saveToken(new Token(jwtToken, createdUser));
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()));
        var user = userDetailsFacade.findUserByEmail(request.email());
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        tokenFacade.saveToken(new Token(jwtToken, user));
        return new AuthenticationResponse(jwtToken, refreshToken);
    }
}
