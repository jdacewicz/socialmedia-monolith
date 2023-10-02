package pl.jdacewicz.socialmediaserver.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jdacewicz.socialmediaserver.authentication.dto.AuthenticationRequest;
import pl.jdacewicz.socialmediaserver.authentication.dto.AuthenticationResponse;
import pl.jdacewicz.socialmediaserver.authentication.dto.RegisterRequest;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade {

    private final AuthenticationService authenticationService;

    public AuthenticationResponse register(RegisterRequest request) {
        return authenticationService.register(request);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }
}
