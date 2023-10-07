package pl.jdacewicz.socialmediaserver.post;

import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.reaction.ReactionUser;

import java.io.IOException;

public interface PostFacade {

    Post getPostById(long id);

    Post getPostByIdAndVisible(long id, boolean visible);

    Post createPost(Post post, MultipartFile image)  throws IOException;

    void changePostVisibility(long id, boolean visible);

    Post reactToPost(long postId, ReactionUser reactionUser);

    void deletePost(long id) throws IOException;
}
