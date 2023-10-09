package pl.jdacewicz.socialmediaserver.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionCounter;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionDto;
import pl.jdacewicz.socialmediaserver.user.UserMapper;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
class ReactionMapperImpl implements ReactionMapper {

    private final UserMapper userMapper;

    @Override
    public List<ReactionCounter> mapToCounter(List<ReactionUser> reactionUsers) {
        List<ReactionCounter> reactionCounters = new ArrayList<>();
        countOccurrences(reactionUsers)
                .forEach((key, value) -> reactionCounters.add(mapToCounter(key, value)));
        return reactionCounters;
    }

    @Override
    public ReactionCounter mapToCounter(Reaction reaction, int count) {
        return new ReactionCounter(mapToDto(reaction), count);
    }

    @Override
    public ReactionDto mapToDto(Reaction reaction) {
        return new ReactionDto(reaction.getId(),
                reaction.getName(),
                reaction.getImageUrl(),
                reaction.getDirectoryUrl());
    }

    @Override
    public ReactionUser mapToReactionUser(ReactionDto reactionDto, UserDto userDto) {
        return new ReactionUser(mapToEntity(reactionDto),
                userMapper.mapToEntity(userDto));
    }

    private Map<Reaction, Integer> countOccurrences(List<ReactionUser> reactionUsers) {
        Map<Reaction, Integer> counter = new HashMap<>();
        for (ReactionUser reactionUser : reactionUsers) {
            var reaction = reactionUser.getReaction();
            if (counter.containsKey(reaction)) {
                counter.put(reaction, counter.get(reaction) + 1);
            } else {
                counter.put(reaction, 1);
            }
        }
        return counter;
    }

    private Reaction mapToEntity(ReactionDto reactionDto) {
        return new Reaction(reactionDto.id(),
                reactionDto.name());
    }
}
