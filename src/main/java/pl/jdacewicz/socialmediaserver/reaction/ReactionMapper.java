package pl.jdacewicz.socialmediaserver.reaction;

import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionCounter;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionDto;

import java.util.List;


public interface ReactionMapper {

    List<ReactionCounter> mapToCounter(List<ReactionUser> reactionUsers);

    ReactionCounter mapToCounter(Reaction reaction, int count);

    ReactionDto mapToDto(Reaction reaction);
}
