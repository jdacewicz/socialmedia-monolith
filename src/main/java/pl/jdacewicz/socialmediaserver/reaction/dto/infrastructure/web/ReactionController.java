package pl.jdacewicz.socialmediaserver.reaction.dto.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.reaction.Reaction;
import pl.jdacewicz.socialmediaserver.reaction.ReactionFacade;
import pl.jdacewicz.socialmediaserver.reaction.ReactionMapper;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionDto;

import java.io.IOException;

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

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ReactionDto createReaction(@RequestPart String name,
                                      @RequestPart MultipartFile image) throws IOException {
        var reaction = new Reaction(name, image.getOriginalFilename());
        var createdReaction = reactionFacade.createReaction(reaction, image);
        return reactionMapper.mapToDto(createdReaction, 1);
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
