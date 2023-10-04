package pl.jdacewicz.socialmediaserver.comment;

import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.user.User;

import java.io.IOException;
import java.util.List;

public interface CommentFacade {

    Comment getCommentById(long id);

    Comment getVisibleCommentById(long id);

    List<Comment> getCommentsByPostId(long postId);

    Comment commentPost(long postId, String content, User creator, MultipartFile image) throws IOException;

    void changeCommentVisibilityById(long id, boolean visibility);

    void deleteComment(long id) throws IOException;
}
