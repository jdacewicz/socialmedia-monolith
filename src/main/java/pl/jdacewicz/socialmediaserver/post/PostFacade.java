package pl.jdacewicz.socialmediaserver.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.post.dto.PostDto;
import pl.jdacewicz.socialmediaserver.post.dto.PostRequest;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class PostFacade {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostDto getVisiblePost(long id) {
        var post = postService.getPostByIdAndVisible(id, true);
        return postMapper.toDto(post);
    }

    public PostDto getPostById(long id) {
        var post = postService.getPostById(id);
        return postMapper.toDto(post);
    }

    public PostDto createPost(PostRequest request, MultipartFile image) throws IOException {
        var post = postMapper.toPost(request, image);
        var createdPost = postService.createPost(post, image);
        return postMapper.toDto(createdPost);
    }

    public boolean changePostVisibility(long id, boolean visible) {
        return postService.changePostVisibility(id, visible);
    }

    public void deletePost(long id) throws IOException {
        postService.deletePost(id);
    }
}
