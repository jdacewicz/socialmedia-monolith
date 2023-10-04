package pl.jdacewicz.socialmediaserver.comment;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CommentFacade {

    Comment getCommentById(long id);

    Comment getVisibleCommentById(long id);

    List<Comment> getCommentsByPostId(long postId);

    Comment createComment(Comment comment, MultipartFile image) throws IOException;

    void changeCommentVisibilityById(long id, boolean visibility);

    void deleteComment(long id) throws IOException;
}
