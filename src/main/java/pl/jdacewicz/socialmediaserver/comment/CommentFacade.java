package pl.jdacewicz.socialmediaserver.comment;

import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.reaction.ReactionUser;

import java.io.IOException;
import java.util.List;

public interface CommentFacade {

    Comment getCommentById(long id);

    Comment getVisibleCommentById(long id);

    List<Comment> getCommentsByPostId(long postId);

    Comment createComment(Comment comment, MultipartFile image) throws IOException;

    void changeCommentVisibilityById(long id, boolean visibility);

    Comment reactToComment(long commentId, ReactionUser reactionUser);

    void deleteComment(long id) throws IOException;
}
