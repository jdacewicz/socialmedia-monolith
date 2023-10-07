package pl.jdacewicz.socialmediaserver.post.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.post.PostFacade;
import pl.jdacewicz.socialmediaserver.post.PostMapper;
import pl.jdacewicz.socialmediaserver.post.dto.PostDto;
import pl.jdacewicz.socialmediaserver.post.dto.PostRequest;
import pl.jdacewicz.socialmediaserver.reaction.ReactionFacade;
import pl.jdacewicz.socialmediaserver.user.UserFacade;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/posts",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PostController {

    private final PostFacade postFacade;
    private final UserFacade userFacade;
    private final ReactionFacade reactionFacade;
    private final PostMapper postMapper;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public PostDto getVisiblePost(@PathVariable long id) {
        var post = postFacade.getPostByIdAndVisible(id, true);
        return postMapper.mapToDto(post);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/peek/{id}")
    public PostDto getPostById(@PathVariable long id) {
        var post = postFacade.getPostById(id);
        return postMapper.mapToDto(post);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public PostDto createPost(@RequestPart PostRequest request,
                              @RequestPart MultipartFile image) throws IOException {
        var loggedUser = userFacade.getLoggedUser();
        var post = postMapper.mapToPost(request, image, loggedUser);
        var createdPost = postFacade.createPost(post, image);
        return postMapper.mapToDto(createdPost);
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
        var reaction = reactionFacade.getReactionById(reactionId);
        var reactedPost = postFacade.reactToPost(postId, reaction);
        return postMapper.mapToDto(reactedPost);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id) throws IOException {
        postFacade.deletePost(id);
    }
}
