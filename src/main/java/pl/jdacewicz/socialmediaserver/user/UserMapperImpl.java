package pl.jdacewicz.socialmediaserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.jdacewicz.socialmediaserver.authentication.dto.RegisterRequest;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto mapToDto(User user) {
        return new UserDto(user.getId(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getProfilePictureUrl());
    }

    @Override
    public User mapToEntity(UserDto userDto) {
        return new User(userDto.id(),
                userDto.email(),
                userDto.firstname(),
                userDto.lastname());
    }
}
