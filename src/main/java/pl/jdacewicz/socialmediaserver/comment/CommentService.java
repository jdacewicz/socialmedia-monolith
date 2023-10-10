package pl.jdacewicz.socialmediaserver.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.comment.dto.CommentDto;
import pl.jdacewicz.socialmediaserver.post.PostFacade;
import pl.jdacewicz.socialmediaserver.post.PostMapper;
import pl.jdacewicz.socialmediaserver.reaction.ReactionUserFacade;
import pl.jdacewicz.socialmediaserver.user.UserFacade;
import pl.jdacewicz.socialmediaserver.user.UserMapper;

import java.io.IOException;
import java.util.List;

import static pl.jdacewicz.socialmediaserver.utils.FileUtils.deleteDirectory;
import static pl.jdacewicz.socialmediaserver.utils.FileUtils.uploadFile;

@Service
@RequiredArgsConstructor
class CommentService implements CommentFacade {

    @Value("${message.comment.not-found}")
    private String notFoundCommentMessage;

    @Value("${message.comment.not-found-visible}")
    private String notFoundVisibleCommentMessage;

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserFacade userFacade;
    private final UserMapper userMapper;
    private final PostFacade postFacade;
    private final PostMapper postMapper;
    private final ReactionUserFacade reactionUserFacade;

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
        var comment = prepareComment(postId, content, image.getOriginalFilename());
        var createdComment = commentRepository.save(comment);
        uploadFile(createdComment.getImageUrl(), image);
        return commentMapper.mapToDto(createdComment);
    }

    @Override
    @Transactional
    public void changeCommentVisibilityById(long id, boolean visible) {
        commentRepository.setVisibleById(id, visible);
    }

    @Override
    public CommentDto reactToComment(long commentId, int reactionId) {
        var comment = getUnmappedCommentById(commentId);
        var reactionUser = reactionUserFacade.createReactionUser(reactionId);
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

    private Comment getUnmappedCommentById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(notFoundCommentMessage));
    }

    private Comment prepareComment(long postId, String content, String imageUrl) {
        var loggedUser = userFacade.getLoggedUser();
        var user = userMapper.mapToEntity(loggedUser);
        var postDto = postFacade.getPostById(postId);
        var post = postMapper.mapToEntity(postDto);
        return new Comment(content, imageUrl, user, post);
    }
}
