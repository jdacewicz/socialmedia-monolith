package pl.jdacewicz.socialmediaserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserFacade {

    @Value("${message.user.not-found}")
    private String notFoundUserMessage;

    private final UserRepository userRepository;

    @Override
    public User getLoggedUser() {
        var user = Optional
                .ofNullable((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        return user.orElseThrow(() -> new UserNotSignedInException(""));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(notFoundUserMessage));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(notFoundUserMessage));
    }
}
