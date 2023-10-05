package pl.jdacewicz.socialmediaserver.comment.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.comment.CommentFacade;
import pl.jdacewicz.socialmediaserver.comment.CommentMapper;
import pl.jdacewicz.socialmediaserver.comment.dto.CommentDto;
import pl.jdacewicz.socialmediaserver.post.PostFacade;
import pl.jdacewicz.socialmediaserver.user.UserFacade;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/comments",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CommentController {

    private final CommentFacade commentFacade;
    private final PostFacade postFacade;
    private final UserFacade userFacade;
    private final CommentMapper commentMapper;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public CommentDto getVisibleCommentById(@PathVariable long id) {
        var comment = commentFacade.getVisibleCommentById(id);
        return commentMapper.mapToDto(comment);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/peek/{id}")
    public CommentDto getCommentById(@PathVariable long id) {
        var comment = commentFacade.getCommentById(id);
        return commentMapper.mapToDto(comment);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/post/{postId}")
    public CommentDto commentPost(@PathVariable long postId,
                                  @RequestPart String content,
                                  @RequestPart MultipartFile image) throws IOException {
        var loggedUser = userFacade.getLoggedUser();
        var post = postFacade.getPostById(postId);
        var comment = commentMapper.mapToComment(content, image, loggedUser, post);
        var createdComment = commentFacade.createComment(comment, image);
        return commentMapper.mapToDto(createdComment);
    }

    public void changeCommentVisibility() {
        throw new UnsupportedOperationException();
    }

    public void deleteComment() {
        throw new UnsupportedOperationException();
    }
}
