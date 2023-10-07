package pl.jdacewicz.socialmediaserver.reaction;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReactionFacade {

    Reaction getReactionById(int id);

    Reaction createReaction(Reaction reaction, MultipartFile image) throws IOException;

    void updateReaction(int id, String name, MultipartFile image) throws IOException;

    void deleteReaction(int id) throws IOException;
}
