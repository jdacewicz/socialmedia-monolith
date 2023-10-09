package pl.jdacewicz.socialmediaserver.comment;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.comment.dto.CommentDto;
import pl.jdacewicz.socialmediaserver.post.PostFacade;
import pl.jdacewicz.socialmediaserver.post.PostMapper;
import pl.jdacewicz.socialmediaserver.reaction.ReactionFacade;
import pl.jdacewicz.socialmediaserver.reaction.ReactionMapper;
import pl.jdacewicz.socialmediaserver.user.UserFacade;
import pl.jdacewicz.socialmediaserver.user.UserMapper;

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
    private final CommentMapper commentMapper;
    private final ReactionMapper reactionMapper;
    private final ReactionFacade reactionFacade;
    private final UserFacade userFacade;
    private final UserMapper userMapper;
    private final PostFacade postFacade;
    private final PostMapper postMapper;

    @Override
    public CommentDto getCommentById(long id) {
        return commentRepository.findById(id)
                .map(commentMapper::mapToDto)
                .orElseThrow(() -> new CommentNotFoundException(notFoundCommentMessage));
    }

    @Override
    public CommentDto getVisibleCommentById(long id) {
        return commentRepository.findByIdAndVisible(id, true)
                .map(commentMapper::mapToDto)
                .orElseThrow(() -> new CommentNotFoundException(notFoundVisibleCommentMessage));
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        var comments = commentRepository.findAllByPostId(postId);
        return commentMapper.mapToDto(comments);
    }

    @Override
    public CommentDto createComment(long postId, String content, MultipartFile image) throws IOException {
        var loggedUser = userFacade.getLoggedUser();
        var user = userMapper.mapToEntity(loggedUser);
        var postDto = postFacade.getPostById(postId);
        var post = postMapper.mapToEntity(postDto);
        var comment = new Comment(content, image.getOriginalFilename(), user, post);
        var createdComment = commentRepository.save(comment);
        uploadImage(createdComment.getImageUrl(), image);
        return commentMapper.mapToDto(createdComment);
    }

    @Override
    @Transactional
    public void changeCommentVisibilityById(long id, boolean visible) {
        commentRepository.setVisibleById(id, visible);
    }

    @Override
    public CommentDto reactToComment(long commentId, int reactionId) {
        var loggedUser = userFacade.getLoggedUser();
        var reaction = reactionFacade.getReactionById(reactionId);
        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(notFoundCommentMessage));
        var reactionUser = reactionMapper.mapToReactionUser(reaction, loggedUser);
        comment.addReactionUser(reactionUser);
        var reactedComment = commentRepository.save(comment);
        return commentMapper.mapToDto(reactedComment);
    }

    @Override
    public void deleteComment(long id) throws IOException {
        var comment = getCommentById(id);
        deleteDirectory(comment.directoryUrl());
        commentRepository.deleteById(id);
    }

    private void uploadImage(String imageUrl, MultipartFile image) throws IOException {
        var directory = new File(imageUrl);
        FileUtils.copyInputStreamToFile(image.getInputStream(), directory);
    }

    private void deleteDirectory(String directoryUrl) throws IOException {
        var directory = new File(directoryUrl);
        FileUtils.deleteDirectory(directory);
    }
}
