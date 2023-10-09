package pl.jdacewicz.socialmediaserver.post;

import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.post.dto.PostDto;

import java.io.IOException;

public interface PostFacade {

    PostDto getPostById(long id);

    PostDto getPostByIdAndVisible(long id, boolean visible);

    PostDto createPost(String content, MultipartFile image)  throws IOException;

    void changePostVisibility(long id, boolean visible);

    PostDto reactToPost(long postId, int reactionId);

    void deletePost(long id) throws IOException;
}
