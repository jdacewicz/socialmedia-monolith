package pl.jdacewicz.socialmediaserver.reaction;

import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.comment.Comment;
import pl.jdacewicz.socialmediaserver.post.Post;

import java.io.IOException;
import java.util.List;

public interface ReactionFacade {

    Reaction getReactionById(int id);

    List<Reaction> getReactions();

    Reaction createReaction(Reaction reaction, MultipartFile image) throws IOException;

    void reactToPost(int reactionId, Post post);

    void reactToComment(int reactionId, Comment comment);

    void updateReaction(Reaction reaction, MultipartFile image) throws IOException;

    void deleteReaction(int id) throws IOException;
}
