package pl.jdacewicz.socialmediaserver.comment;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommentFacade {

    Comment getCommentById(long id);

    Comment getVisibleCommentById(long id);

    List<Comment> getComments(long postId);

    Comment commentPost(long postId, String content, MultipartFile image);

    void hideComment(long id);

    void showComment(long id);

    void deleteComment(long id);
}
