package pl.jdacewicz.socialmediaserver.user;

import org.springframework.stereotype.Component;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

@Component
class UserMapperImpl implements UserMapper {

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
        return User.builder()
                .id(userDto.id())
                .email(userDto.email())
                .firstname(userDto.firstname())
                .lastname(userDto.lastname())
                .build();
    }
}
