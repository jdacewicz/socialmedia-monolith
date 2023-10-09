package pl.jdacewicz.socialmediaserver.comment;

import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.comment.dto.CommentDto;
import pl.jdacewicz.socialmediaserver.reaction.ReactionUser;

import java.io.IOException;
import java.util.List;

public interface CommentFacade {

    CommentDto getCommentById(long id);

    CommentDto getVisibleCommentById(long id);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto createComment(long postId, String content, MultipartFile image) throws IOException;

    void changeCommentVisibilityById(long id, boolean visibility);

    CommentDto reactToComment(long commentId, int reactionId);

    void deleteComment(long id) throws IOException;
}
