package pl.jdacewicz.socialmediaserver.post.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.post.PostFacade;
import pl.jdacewicz.socialmediaserver.post.dto.PostDto;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/posts",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PostController {

    private final PostFacade postFacade;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public PostDto getVisiblePost(@PathVariable long id) {
        return postFacade.getPostByIdAndVisible(id, true);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/peek/{id}")
    public PostDto getPostById(@PathVariable long id) {
        return postFacade.getPostById(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public PostDto createPost(@RequestPart String content,
                              @RequestPart MultipartFile image) throws IOException {
        return postFacade.createPost(content, image);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public void changePostVisibility(@PathVariable long id,
                                     @RequestParam boolean visible) {
        postFacade.changePostVisibility(id, visible);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{postId}/react/{reactionId}")
    public PostDto reactToPost(@PathVariable long postId,
                               @PathVariable int reactionId) {
        return postFacade.reactToPost(postId, reactionId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id) throws IOException {
        postFacade.deletePost(id);
    }
}
