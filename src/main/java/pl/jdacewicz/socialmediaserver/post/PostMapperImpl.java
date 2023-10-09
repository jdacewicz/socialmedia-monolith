package pl.jdacewicz.socialmediaserver.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jdacewicz.socialmediaserver.comment.CommentMapper;
import pl.jdacewicz.socialmediaserver.post.dto.PostDto;
import pl.jdacewicz.socialmediaserver.reaction.ReactionMapper;
import pl.jdacewicz.socialmediaserver.user.UserMapper;

@Component
@RequiredArgsConstructor
class PostMapperImpl implements PostMapper {

    private final UserMapper userMapper;
    private final CommentMapper commentMapper;
    private final ReactionMapper reactionMapper;

    @Override
    public PostDto mapToDto(Post post) {
        return new PostDto(post.getId(),
                post.getCreationDateTime(),
                post.getContent(),
                post.getImageUrl(),
                post.getDirectoryUrl(),
                userMapper.mapToDto(post.getCreator()),
                commentMapper.mapToDto(post.getComments()),
                reactionMapper.mapToCounter(post.getReactionUsers()));
    }

    @Override
    public Post mapToEntity(PostDto postDto) {
        return new Post(postDto.id(),
                postDto.creationDateTime(),
                postDto.content(),
                userMapper.mapToEntity(postDto.creator()));
    }
}
