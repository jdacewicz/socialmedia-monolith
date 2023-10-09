package pl.jdacewicz.socialmediaserver.reaction;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionDto;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
class ReactionService implements ReactionFacade{

    @Value("${message.reaction.not-found}")
    private String notFoundReactionMessage;

    private final ReactionRepository reactionRepository;
    private final ReactionMapper reactionMapper;

    @Override
    public ReactionDto getReactionById(int id) {
        return reactionRepository.findById(id)
                .map(reactionMapper::mapToDto)
                .orElseThrow(() -> new ReactionNotFoundException(notFoundReactionMessage));
    }

    @Override
    public ReactionDto createReaction(String name, MultipartFile image) throws IOException {
        var reaction = new Reaction(name, image.getOriginalFilename());
        var createdReaction = reactionRepository.save(reaction);
        uploadImage(createdReaction.getImageUrl(), image);
        return reactionMapper.mapToDto(createdReaction);
    }

    @Override
    @Transactional
    public void updateReaction(int id, String name, MultipartFile image) throws IOException {
        var reaction = getReactionById(id);
        uploadImage(reaction.imageUrl(), image);
        reactionRepository.setReactionNameAndImageNameById(id, name,
                image.getOriginalFilename());
    }

    @Override
    public void deleteReaction(int id) throws IOException {
        var reaction = getReactionById(id);
        deleteDirectory(reaction.directoryUrl());
        reactionRepository.deleteById(id);
    }

    private void uploadImage(String imageUrl, MultipartFile image) throws IOException {
        var directory = new File(imageUrl);
        FileUtils.copyInputStreamToFile(image.getInputStream(), directory);
    }

    private void deleteDirectory(String directoryUrl) throws IOException {
        var directory = new File(directoryUrl);
        FileUtils.deleteDirectory(directory);
    }
}
