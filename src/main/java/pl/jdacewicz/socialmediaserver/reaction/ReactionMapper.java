package pl.jdacewicz.socialmediaserver.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ReactionMapper {

    public List<ReactionDto> mapToDto(List<Reaction> reactions) {
        List<ReactionDto> reactionsDto = new ArrayList<>();
        countOccurrences(reactions)
                .forEach((key, value) -> reactionsDto.add(mapToDto(key, value)));
        return reactionsDto;
    }

    public ReactionDto mapToDto(Reaction reaction, int count) {
        return new ReactionDto(reaction.getId(),
                reaction.getName(),
                reaction.getImageUrl(),
                count);
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