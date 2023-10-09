package pl.jdacewicz.socialmediaserver.reaction;

import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionDto;

import java.io.IOException;

public interface ReactionFacade {

    ReactionDto getReactionById(int id);

    ReactionDto createReaction(String name, MultipartFile image) throws IOException;

    void updateReaction(int id, String name, MultipartFile image) throws IOException;

    void deleteReaction(int id) throws IOException;
}
