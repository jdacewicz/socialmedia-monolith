package pl.jdacewicz.socialmediaserver.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.comment.CommentMapper;
import pl.jdacewicz.socialmediaserver.post.dto.PostDto;
import pl.jdacewicz.socialmediaserver.post.dto.PostRequest;
import pl.jdacewicz.socialmediaserver.reaction.ReactionMapper;
import pl.jdacewicz.socialmediaserver.user.User;
import pl.jdacewicz.socialmediaserver.user.UserMapper;

@Component
@RequiredArgsConstructor
public class PostMapper {

    private final UserMapper userMapper;
    private final CommentMapper commentMapper;
    private final ReactionMapper reactionMapper;

    public PostDto mapToDto(Post post) {
        return new PostDto(post.getId(),
                post.getCreationDateTime(),
                post.getContent(),
                post.getImageUrl(),
                userMapper.mapToDto(post.getCreator()),
                commentMapper.mapToDto(post.getComments()),
                reactionMapper.mapToDto(post.getReactions()));
    }

    public Post mapToPost(PostRequest request, MultipartFile image, User loggedUser) {
        return new Post(request.content(),
                image.getOriginalFilename(),
                loggedUser);
    }
}
