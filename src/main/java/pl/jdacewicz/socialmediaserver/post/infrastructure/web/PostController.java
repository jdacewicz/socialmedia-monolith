package pl.jdacewicz.socialmediaserver.post.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.post.PostMapper;
import pl.jdacewicz.socialmediaserver.post.PostRequest;
import pl.jdacewicz.socialmediaserver.post.PostService;
import pl.jdacewicz.socialmediaserver.post.dto.PostDto;

import java.io.IOException;

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

    @PostMapping
    public PostDto createPost(@RequestPart PostRequest request,
                              @RequestPart MultipartFile image) throws IOException {
        var createdPost = postService.createPost(request, image);
        return postMapper.toDto(createdPost);
    }

    @PutMapping("/{id}")
    public boolean changePostVisibility(@PathVariable long id,
                                        @RequestParam boolean visible) {
        return postService.changePostVisibility(id, visible);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id) throws IOException {
        postService.deletePost(id);
    }
}
