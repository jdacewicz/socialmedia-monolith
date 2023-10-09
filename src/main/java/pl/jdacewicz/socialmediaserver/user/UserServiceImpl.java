package pl.jdacewicz.socialmediaserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserFacade {

    @Value("${message.user.not-found}")
    private String notFoundUserMessage;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getLoggedUser() {
        var user = Optional
                .ofNullable((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        return user.map(userMapper::mapToDto)
                .orElseThrow(() -> new UserNotSignedInException(""));
    }

    @Override
    public UserDto createUser(User user) {
        var createdUser = userRepository.save(user);
        return userMapper.mapToDto(createdUser);
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::mapToDto)
                .orElseThrow(() -> new UsernameNotFoundException(notFoundUserMessage));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(notFoundUserMessage));
    }
}
