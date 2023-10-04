package pl.jdacewicz.socialmediaserver.post.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.post.Post;
import pl.jdacewicz.socialmediaserver.post.PostFacade;
import pl.jdacewicz.socialmediaserver.post.dto.PostDto;
import pl.jdacewicz.socialmediaserver.post.dto.PostRequest;
import pl.jdacewicz.socialmediaserver.user.User;
import pl.jdacewicz.socialmediaserver.user.dto.UserDto;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/posts",
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PostController {

    private final PostFacade postFacade;

    @GetMapping("/{id}")
    public PostDto getVisiblePost(@PathVariable long id) {
        var post = postFacade.getPostByIdAndVisible(id, true);
        return mapToDto(post);
    }

    @GetMapping("/peek/{id}")
    public PostDto getPostById(@PathVariable long id) {
        var post = postFacade.getPostById(id);
        return mapToDto(post);
    }

    @PostMapping
    public PostDto createPost(@RequestPart PostRequest request,
                              @RequestPart MultipartFile image) throws IOException {
        var post = mapToPost(request, image);
        var createdPost = postFacade.createPost(post, image);
        return mapToDto(createdPost);
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

    private Post mapToPost(PostRequest request, MultipartFile image) {
        return new Post(request.content(),
                image.getOriginalFilename());
    }

    private PostDto mapToDto(Post post) {
        return new PostDto(post.getId(),
                post.getCreationDateTime(),
                post.getContent(),
                post.getImageUrl(),
                mapToDto(post.getCreator()));
    }

    private UserDto mapToDto(User user) {
        return new UserDto(user.getFirstname(),
                user.getLastname(),
                user.getProfilePictureUrl());
    }

    private User getLoggedUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    private Post mapToPost(PostRequest request, MultipartFile image, User loggedUser) {
        return new Post(request.content(),
                image.getOriginalFilename(),
                loggedUser);
    }
}
