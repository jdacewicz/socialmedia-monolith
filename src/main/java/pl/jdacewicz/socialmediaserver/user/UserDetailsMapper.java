package pl.jdacewicz.socialmediaserver.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.jdacewicz.socialmediaserver.user.dto.UserDetailsDto;

@Component
public class UserDetailsMapper {

    UserDetailsDto toDto(UserDetails userDetails) {
        return new UserDetailsDto(userDetails.getUsername());
    }
}
