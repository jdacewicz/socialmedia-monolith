package pl.jdacewicz.socialmediaserver.comment.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.comment.Comment;
import pl.jdacewicz.socialmediaserver.comment.CommentFacade;
import pl.jdacewicz.socialmediaserver.comment.dto.CommentDto;
import pl.jdacewicz.socialmediaserver.post.Post;
import pl.jdacewicz.socialmediaserver.post.PostFacade;
import pl.jdacewicz.socialmediaserver.user.User;
import pl.jdacewicz.socialmediaserver.user.UserFacade;
import pl.jdacewicz.socialmediaserver.user.UserMapper;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/comments",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CommentController {

    private final CommentFacade commentFacade;
    private final PostFacade postFacade;
    private final UserFacade userFacade;
    private final UserMapper userMapper;

    public CommentDto getVisibleCommentById() {
        throw new UnsupportedOperationException();
    }

    public CommentDto getCommentById() {
        throw new UnsupportedOperationException();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/post/{postId}")
    public CommentDto commentPost(@PathVariable long postId,
                                  @RequestPart String content,
                                  @RequestPart MultipartFile image) throws IOException {
        var loggedUser = userFacade.getLoggedUser();
        var post = postFacade.getPostById(postId);
        var comment = mapToComment(content, image, loggedUser, post);
        var createdComment = commentFacade.createComment(comment, image);
        return mapToDto(createdComment);
    }

    public void changeCommentVisibility() {
        throw new UnsupportedOperationException();
    }

    public void deleteComment() {
        throw new UnsupportedOperationException();
    }

    private Comment mapToComment(String content, MultipartFile image, User loggedUser, Post post) {
        return new Comment(content,
                image.getOriginalFilename(),
                loggedUser,
                post);
    }

    private CommentDto mapToDto(Comment comment) {
        return new CommentDto(comment.getId(),
                comment.getCreationDateTime(),
                comment.getContent(),
                comment.getImageUrl(),
                userMapper.mapToDto(comment.getCreator()));
    }
}
