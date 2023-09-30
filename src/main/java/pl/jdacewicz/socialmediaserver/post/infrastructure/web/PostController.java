package pl.jdacewicz.socialmediaserver.post.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.post.PostFacade;
import pl.jdacewicz.socialmediaserver.post.dto.PostDto;
import pl.jdacewicz.socialmediaserver.post.dto.PostRequest;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/posts",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PostController {

    private final PostFacade postFacade;

    @GetMapping("/{id}")
    public PostDto getVisiblePost(@PathVariable long id) {
        return postFacade.getVisiblePost(id);
    }

    @GetMapping("/peek/{id}")
    public PostDto getPostById(@PathVariable long id) {
        return postFacade.getPostById(id);
    }

    @PostMapping
    public PostDto createPost(@RequestPart PostRequest request,
                              @RequestPart MultipartFile image) throws IOException {
        return postFacade.createPost(request, image);
    }

    @PutMapping("/{id}")
    public boolean changePostVisibility(@PathVariable long id,
                                        @RequestParam boolean visible) {
        return postFacade.changePostVisibility(id, visible);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id) throws IOException {
        postFacade.deletePost(id);
    }
}
