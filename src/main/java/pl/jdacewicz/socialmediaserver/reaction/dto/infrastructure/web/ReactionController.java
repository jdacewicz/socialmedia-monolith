package pl.jdacewicz.socialmediaserver.reaction.dto.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jdacewicz.socialmediaserver.reaction.ReactionFacade;
import pl.jdacewicz.socialmediaserver.reaction.ReactionMapper;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionDto;

@RestController
@RequestMapping(value = "/api/reactions",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReactionController {

    private final ReactionFacade reactionFacade;
    private final ReactionMapper reactionMapper;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ReactionDto getReactionById(@PathVariable int id) {
        var reaction = reactionFacade.getReactionById(id);
        return reactionMapper.mapToDto(reaction, 1);
    }

    public ReactionDto createReaction() {
        throw new UnsupportedOperationException();
    }

    public void reactToPost() {
        throw new UnsupportedOperationException();
    }

    public void reactToComment() {
        throw new UnsupportedOperationException();
    }

    public void updateReaction() {
        throw new UnsupportedOperationException();
    }

    public void deleteReaction() {
        throw new UnsupportedOperationException();
    }
}
