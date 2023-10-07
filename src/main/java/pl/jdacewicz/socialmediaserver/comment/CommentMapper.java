package pl.jdacewicz.socialmediaserver.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.comment.dto.CommentDto;
import pl.jdacewicz.socialmediaserver.post.Post;
import pl.jdacewicz.socialmediaserver.reaction.ReactionMapper;
import pl.jdacewicz.socialmediaserver.user.User;
import pl.jdacewicz.socialmediaserver.user.UserMapper;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final UserMapper userMapper;
    private final ReactionMapper reactionMapper;

    public List<CommentDto> mapToDto(List<Comment> comments) {
        return comments
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public CommentDto mapToDto(Comment comment) {
        return new CommentDto(comment.getId(),
                comment.getCreationDateTime(),
                comment.getContent(),
                comment.getImageUrl(),
                userMapper.mapToDto(comment.getCreator()),
                reactionMapper.mapToCounter(comment.getReactionUsers()));
    }

    public Comment mapToComment(String content, MultipartFile image, User loggedUser, Post post) {
        return new Comment(content,
                image.getOriginalFilename(),
                loggedUser,
                post);
    }
}
