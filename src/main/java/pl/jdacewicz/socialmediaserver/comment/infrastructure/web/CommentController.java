package pl.jdacewicz.socialmediaserver.comment.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.comment.CommentFacade;
import pl.jdacewicz.socialmediaserver.comment.dto.CommentDto;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/comments",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CommentController {

    private final CommentFacade commentFacade;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public CommentDto getVisibleCommentById(@PathVariable long id) {
        return commentFacade.getVisibleCommentById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/peek/{id}")
    public CommentDto getCommentById(@PathVariable long id) {
        return commentFacade.getCommentById(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/post/{postId}")
    public CommentDto commentPost(@PathVariable long postId,
                                  @RequestPart String content,
                                  @RequestPart MultipartFile image) throws IOException {
        return commentFacade.createComment(postId, content, image);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public void changeCommentVisibility(@PathVariable long id,
                                        @RequestParam boolean visible) {
        commentFacade.changeCommentVisibilityById(id, visible);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{commentId}/react/{reactionId}")
    public CommentDto reactToComment(@PathVariable long commentId,
                                     @PathVariable int reactionId) {
        return commentFacade.reactToComment(commentId, reactionId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable long id) throws IOException {
        commentFacade.deleteComment(id);
    }
}
