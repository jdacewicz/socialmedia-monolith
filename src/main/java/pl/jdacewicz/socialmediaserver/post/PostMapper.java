package pl.jdacewicz.socialmediaserver.post;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.post.dto.PostDto;
import pl.jdacewicz.socialmediaserver.post.dto.PostRequest;

@Component
class PostMapper {

    Post toPost(PostRequest request, MultipartFile file) {
        return new Post(request.content(),
                file.getName());
    }

    PostDto toDto(Post post) {
        return new PostDto(post.getId(),
                post.getCreationDateTime(),
                post.getContent(),
                post.getImageUrl());
    }
}
