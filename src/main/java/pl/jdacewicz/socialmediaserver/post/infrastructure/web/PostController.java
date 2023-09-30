package pl.jdacewicz.socialmediaserver.post.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jdacewicz.socialmediaserver.post.PostMapper;
import pl.jdacewicz.socialmediaserver.post.PostService;
import pl.jdacewicz.socialmediaserver.post.dto.PostDto;

@RestController
@RequestMapping(value = "/api/posts",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping("/{id}")
    public PostDto getVisiblePost(@PathVariable long id) {
        var post = postService.getPostByIdAndVisible(id, true);
        return postMapper.toDto(post);
    }

    @GetMapping("/peek/{id}")
    public PostDto getPostById(@PathVariable long id) {
        var post = postService.getPostById(id);
        return postMapper.toDto(post);
    }

    public PostDto createPost() {
        throw new UnsupportedOperationException();
    }

    public boolean changePostVisibility() {
        throw new UnsupportedOperationException();
    }

    public void deletePost() {
        throw new UnsupportedOperationException();
    }
}
