package pl.jdacewicz.socialmediaserver.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserFacade extends UserDetailsService {

    User getLoggedUser();

    User createUser(User user);

    User findUserByEmail(String email);
}
