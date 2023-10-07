package pl.jdacewicz.socialmediaserver.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionCounter;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ReactionMapper {

    public List<ReactionCounter> mapToCounter(List<Reaction> reactions) {
        List<ReactionCounter> reactionCounters = new ArrayList<>();
        countOccurrences(reactions)
                .forEach((key, value) -> reactionCounters.add(mapToCounter(key, value)));
        return reactionCounters;
    }

    public ReactionCounter mapToCounter(Reaction reaction, int count) {
        return new ReactionCounter(mapToDto(reaction), count);
    }

    public ReactionDto mapToDto(Reaction reaction) {
        return new ReactionDto(reaction.getId(),
                reaction.getName(),
                reaction.getImageUrl());
    }

    private Map<Reaction, Integer> countOccurrences(List<Reaction> reactions) {
        Map<Reaction, Integer> counter = new HashMap<>();
        for (Reaction reaction : reactions) {
            if (counter.containsKey(reaction)) {
                counter.put(reaction, counter.get(reaction) + 1);
            } else {
                counter.put(reaction, 1);
            }
        }
        return counter;
    }
}
