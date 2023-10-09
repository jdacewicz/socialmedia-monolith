package pl.jdacewicz.socialmediaserver.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

public interface UserFacade extends UserDetailsService {

    UserDto getLoggedUser();

    UserDto createUser(User user);

    UserDto findUserByEmail(String email);
}
