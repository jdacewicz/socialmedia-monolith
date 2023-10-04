package pl.jdacewicz.socialmediaserver.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
class CommentService implements CommentFacade {

    @Value("${message.comment.not-found}")
    private String notFoundCommentMessage;

    @Value("${message.comment.not-found-visible}")
    private String notFoundVisibleCommentMessage;

    private final CommentRepository commentRepository;

    @Override
    public Comment getCommentById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(notFoundCommentMessage));
    }

    @Override
    public Comment getVisibleCommentById(long id) {
        return commentRepository.findByIdAndVisible(id, true)
                .orElseThrow(() -> new CommentNotFoundException(notFoundVisibleCommentMessage));
    }

    @Override
    public List<Comment> getCommentsByPostId(long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    public Comment commentPost(long postId, String content, MultipartFile image) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void hideComment(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showComment(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteComment(long id) {
        throw new UnsupportedOperationException();
    }
}
