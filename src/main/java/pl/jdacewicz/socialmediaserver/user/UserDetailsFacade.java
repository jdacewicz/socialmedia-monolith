package pl.jdacewicz.socialmediaserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jdacewicz.socialmediaserver.user.dto.UserDetailsDto;

@Component
@RequiredArgsConstructor
public class UserDetailsFacade {

    private final UserDetailsServiceImpl userDetailsService;
    private final UserDetailsMapper userDetailsMapper;

    public UserDetailsDto getUserByEmail(String email) {
        var foundUser = userDetailsService.loadUserByUsername(email);
        return userDetailsMapper.toDto(foundUser);
    }

    public UserDetailsDto createUser(User user) {
        var createdUser = userDetailsService.createUser(user);
        return userDetailsMapper.toDto(createdUser);
    }
}
