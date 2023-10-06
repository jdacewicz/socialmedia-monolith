package pl.jdacewicz.socialmediaserver.reaction;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public Reaction createReaction(Reaction reaction, MultipartFile image) throws IOException {
        var createdReaction = reactionRepository.save(reaction);
        var directory = new File(createdReaction.getImageUrl());
        FileUtils.copyInputStreamToFile(image.getInputStream(), directory);
        return createdReaction;
    }

    @Override
    public void reactToPost(int reactionId, Post post) {
        var reaction = getReactionById(reactionId);
        reaction.getPosts()
                .add(post);
        reactionRepository.save(reaction);

    }

    @Override
    public void reactToComment(int reactionId, Comment comment) {
        var reaction = getReactionById(reactionId);
        reaction.getComments()
                .add(comment);
        reactionRepository.save(reaction);
    }

    @Override
    @Transactional
    public void updateReaction(int id, String name, MultipartFile image) throws IOException {
        var reaction = getReactionById(id);
        var directory = new File(reaction.getImageUrl());
        FileUtils.copyInputStreamToFile(image.getInputStream(), directory);
        reactionRepository.setReactionNameAndImageNameById(id, name,
                image.getOriginalFilename());
    }

    @Override
    public void deleteReaction(int id) throws IOException {
        var directory = new File(getReactionById(id)
                .getDirectoryUrl());
        FileUtils.deleteDirectory(directory);
        reactionRepository.deleteById(id);
    }
}
