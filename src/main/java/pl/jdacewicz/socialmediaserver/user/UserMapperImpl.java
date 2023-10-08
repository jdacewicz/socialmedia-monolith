package pl.jdacewicz.socialmediaserver.user;

import org.springframework.stereotype.Component;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

@Component
public class UserMapperImpl implements UserMapper{

    @Override
    public UserDto mapToDto(User user) {
        return new UserDto(user.getFirstname(),
                user.getLastname(),
                user.getProfilePictureUrl());
    }
}
