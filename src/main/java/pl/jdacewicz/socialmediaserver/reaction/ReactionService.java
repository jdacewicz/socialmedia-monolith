package pl.jdacewicz.socialmediaserver.reaction;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
    public Reaction createReaction(Reaction reaction, MultipartFile image) throws IOException {
        var createdReaction = reactionRepository.save(reaction);
        var directory = new File(createdReaction.getImageUrl());
        FileUtils.copyInputStreamToFile(image.getInputStream(), directory);
        return createdReaction;
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
