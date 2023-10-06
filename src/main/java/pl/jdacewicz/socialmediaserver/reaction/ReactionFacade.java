package pl.jdacewicz.socialmediaserver.reaction;

import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.comment.Comment;
import pl.jdacewicz.socialmediaserver.post.Post;

import java.io.IOException;
import java.util.List;

public interface ReactionFacade {

    Reaction getReactionById(int id);

    List<Reaction> getReactions();

    Reaction createReaction(String name, MultipartFile image) throws IOException;

    void reactToPost(Reaction reaction, Post post);

    void reactToComment(Reaction reaction, Comment comment);

    void updateReaction(int id, String name, MultipartFile image) throws IOException;

    void deleteReaction(int id) throws IOException;
}
