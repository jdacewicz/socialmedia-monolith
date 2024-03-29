package pl.jdacewicz.socialmediaserver.reaction;

import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionCounter;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionDto;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

import java.util.List;


public interface ReactionMapper {

    List<ReactionCounter> mapToCounter(List<ReactionUser> reactionUsers);

    ReactionCounter mapToCounter(Reaction reaction, long count);

    ReactionDto mapToDto(Reaction reaction);

    ReactionUser mapToReactionUser(ReactionDto reactionDto, UserDto userDto);
}
