package pl.jdacewicz.socialmediaserver.post;

import pl.jdacewicz.socialmediaserver.post.dto.PostDto;


public interface PostMapper {

    PostDto mapToDto(Post post);

    Post mapToEntity(PostDto postDto);
}
