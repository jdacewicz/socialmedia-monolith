package pl.jdacewicz.socialmediaserver.reaction.dto.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.comment.CommentFacade;
import pl.jdacewicz.socialmediaserver.post.PostFacade;
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
    private final PostFacade postFacade;
    private final CommentFacade commentFacade;
    private final ReactionMapper reactionMapper;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ReactionDto getReactionById(@PathVariable int id) {
        var reaction = reactionFacade.getReactionById(id);
        return reactionMapper.mapToDto(reaction, 1);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ReactionDto createReaction(@RequestPart String name,
                                      @RequestPart MultipartFile image) throws IOException {
        var reaction = new Reaction(name, image.getOriginalFilename());
        var createdReaction = reactionFacade.createReaction(reaction, image);
        return reactionMapper.mapToDto(createdReaction, 1);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{reactionId}/post/{postId}")
    public void reactToPost(@PathVariable int reactionId,
                            @PathVariable long postId) {
        var post = postFacade.getPostById(postId);
        reactionFacade.reactToPost(reactionId, post);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{reactionId}/comment/{commentId}")
    public void reactToComment(@PathVariable int reactionId,
                               @PathVariable long commentId) {
        var comment = commentFacade.getCommentById(commentId);
        reactionFacade.reactToComment(reactionId, comment);
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
