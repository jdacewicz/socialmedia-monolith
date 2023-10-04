package pl.jdacewicz.socialmediaserver.post.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.post.Post;
import pl.jdacewicz.socialmediaserver.post.PostFacade;
import pl.jdacewicz.socialmediaserver.post.dto.PostDto;
import pl.jdacewicz.socialmediaserver.post.dto.PostRequest;
import pl.jdacewicz.socialmediaserver.user.User;
import pl.jdacewicz.socialmediaserver.user.UserFacade;
import pl.jdacewicz.socialmediaserver.user.UserMapper;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/posts",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PostController {

    private final PostFacade postFacade;
    private final UserFacade userFacade;
    private final UserMapper userMapper;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public PostDto getVisiblePost(@PathVariable long id) {
        var post = postFacade.getPostByIdAndVisible(id, true);
        return mapToDto(post);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/peek/{id}")
    public PostDto getPostById(@PathVariable long id) {
        var post = postFacade.getPostById(id);
        return mapToDto(post);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public PostDto createPost(@RequestPart PostRequest request,
                              @RequestPart MultipartFile image) throws IOException {
        var loggedUser = userFacade.getLoggedUser();
        var post = mapToPost(request, image, loggedUser);
        var createdPost = postFacade.createPost(post, image);
        return mapToDto(createdPost);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public void changePostVisibility(@PathVariable long id,
                                     @RequestParam boolean visible) {
        postFacade.changePostVisibility(id, visible);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id) throws IOException {
        postFacade.deletePost(id);
    }

    private PostDto mapToDto(Post post) {
        return new PostDto(post.getId(),
                post.getCreationDateTime(),
                post.getContent(),
                post.getImageUrl(),
                userMapper.mapToDto(post.getCreator()));
    }

    private Post mapToPost(PostRequest request, MultipartFile image, User loggedUser) {
        return new Post(request.content(),
                image.getOriginalFilename(),
                loggedUser);
    }
}
