package pl.jdacewicz.socialmediaserver.reaction.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.reaction.ReactionFacade;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionDto;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/reactions",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReactionController {

    private final ReactionFacade reactionFacade;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ReactionDto getReactionById(@PathVariable int id) {
        return reactionFacade.getReactionById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ReactionDto createReaction(@RequestPart String name,
                                      @RequestPart MultipartFile image) throws IOException {
        return reactionFacade.createReaction(name, image);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public void updateReaction(@PathVariable int id,
                               @RequestPart String name,
                               @RequestPart MultipartFile image) throws IOException {
        reactionFacade.updateReaction(id, name, image);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteReaction(@PathVariable int id) throws IOException {
        reactionFacade.deleteReaction(id);
    }
}
