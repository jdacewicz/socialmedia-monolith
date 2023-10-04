package pl.jdacewicz.socialmediaserver.user;

import org.springframework.stereotype.Component;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

@Component
public class UserMapper {

    public UserDto mapToDto(User user) {
        return new UserDto(user.getFirstname(),
                user.getLastname(),
                user.getProfilePictureUrl());
    }
}
