package pl.jdacewicz.socialmediaserver.comment;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.reaction.ReactionUser;

import java.io.File;
import java.io.IOException;
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
    public Comment createComment(Comment comment, MultipartFile image) throws IOException {
        var createdComment = commentRepository.save(comment);
        var directory = new File(createdComment.getImageUrl());
        FileUtils.copyInputStreamToFile(image.getInputStream(), directory);
        return createdComment;
    }

    @Override
    @Transactional
    public void changeCommentVisibilityById(long id, boolean visible) {
        commentRepository.setVisibleById(id, visible);
    }

    @Override
    public Comment reactToComment(long commentId, ReactionUser reactionUser) {
        var comment = getCommentById(commentId);
        reactionUser.getComments().add(comment);
        comment.getReactionUsers().add(reactionUser);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(long id) throws IOException {
        var directory = new File(getCommentById(id)
                .getDirectoryUrl());
        FileUtils.deleteDirectory(directory);
        commentRepository.deleteById(id);
    }
}
