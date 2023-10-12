package pl.jdacewicz.socialmediaserver.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.reaction.dto.ReactionDto;

import java.io.IOException;
import java.util.List;

import static pl.jdacewicz.socialmediaserver.utils.FileUtils.deleteDirectory;
import static pl.jdacewicz.socialmediaserver.utils.FileUtils.uploadFile;

@Service
@RequiredArgsConstructor
class ReactionService implements ReactionFacade{

    @Value("${message.reaction.not-found}")
    private String notFoundReactionMessage;

    private final ReactionRepository reactionRepository;
    private final ReactionMapper reactionMapper;

    @Override
    public ReactionDto getReactionById(int id) {
        return reactionMapper.mapToDto(getUnmappedReaction(id));
    }

    @Override
    public ReactionDto createReaction(String name, MultipartFile image) throws IOException {
        var reaction = new Reaction(name, image.getOriginalFilename());
        var createdReaction = reactionRepository.save(reaction);
        uploadFile(createdReaction.getImageUrl(), image);
        return reactionMapper.mapToDto(createdReaction);
    }

    @Override
    @Transactional
    public void updateReaction(int id, String name, MultipartFile image) throws IOException {
        var reaction = getReactionById(id);
        uploadFile(reaction.imageUrl(), image);
        reactionRepository.setReactionNameAndImageNameById(id, name,
                image.getOriginalFilename());
    }

    @Override
    public void deleteReaction(int id) throws IOException {
        var reaction = getUnmappedReaction(id);
        removeRemoveRelationsWithPostsAndComments(reaction.getReactionUsers());
        deleteDirectory(reaction.getDirectoryUrl());
        reactionRepository.save(reaction);
        reactionRepository.deleteById(id);
    }

    private Reaction getUnmappedReaction(int id) {
        return reactionRepository.findById(id)
                .orElseThrow(() -> new ReactionNotFoundException(notFoundReactionMessage));
    }

    private void removeRemoveRelationsWithPostsAndComments(List<ReactionUser> reactionUsers) {
        reactionUsers.forEach(reactionUser -> {
            reactionUser.removeRelationWithPosts();
            reactionUser.removeRelationWithComments();
        });
    }
}
