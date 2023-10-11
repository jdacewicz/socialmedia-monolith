package pl.jdacewicz.socialmediaserver.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionCounter;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionDto;
import pl.jdacewicz.socialmediaserver.user.UserMapper;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

import static pl.jdacewicz.socialmediaserver.reaction.ReactionUser.countOccurrences;

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
    public ReactionCounter mapToCounter(Reaction reaction, long count) {
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

    private Reaction mapToEntity(ReactionDto reactionDto) {
        return new Reaction(reactionDto.id(),
                reactionDto.name());
    }
}
