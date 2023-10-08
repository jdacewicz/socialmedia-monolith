package pl.jdacewicz.socialmediaserver.user;

import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

public interface UserMapper {

    UserDto mapToDto(User user);
}
