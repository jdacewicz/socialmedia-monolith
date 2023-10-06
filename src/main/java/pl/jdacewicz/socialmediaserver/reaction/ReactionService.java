package pl.jdacewicz.socialmediaserver.reaction;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.comment.Comment;
import pl.jdacewicz.socialmediaserver.post.Post;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
class ReactionService implements ReactionFacade{

    @Value("${message.reaction.not-found}")
    private String notFoundReactionMessage;

    private final ReactionRepository reactionRepository;

    @Override
    public Reaction getReactionById(int id) {
        return reactionRepository.findById(id)
                .orElseThrow(() -> new ReactionNotFoundException(notFoundReactionMessage));
    }

    @Override
    public List<Reaction> getReactions() {
        return reactionRepository.findAll();
    }

    @Override
    public Reaction createReaction(String name, MultipartFile image) throws IOException {
        return null;
    }

    @Override
    public void reactToPost(Reaction reaction, Post post) {

    }

    @Override
    public void reactToComment(Reaction reaction, Comment comment) {

    }

    @Override
    public void updateReaction(int id, String name, MultipartFile image) throws IOException {

    }

    @Override
    public void deleteReaction(int id) throws IOException {
        var directory = new File(getReactionById(id)
                .getDirectoryUrl());
        FileUtils.deleteDirectory(directory);
        reactionRepository.deleteById(id);
    }
}
