package pl.jdacewicz.socialmediaserver.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailsFacade extends UserDetailsService {

    User createUser(User user);

    User findUserByEmail(String email);
}
