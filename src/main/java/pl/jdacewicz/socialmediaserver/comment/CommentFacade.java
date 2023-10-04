package pl.jdacewicz.socialmediaserver.comment;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommentFacade {

    Comment getCommentById(long id);

    Comment getVisibleCommentById(long id);

    List<Comment> getCommentsByPostId(long postId);

    Comment commentPost(long postId, String content, MultipartFile image);

    void changeCommentVisibilityById(long id, boolean visibility);

    void deleteComment(long id);
}
