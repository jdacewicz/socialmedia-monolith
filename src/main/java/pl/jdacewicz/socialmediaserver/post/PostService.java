package pl.jdacewicz.socialmediaserver.post;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.post.dto.PostDto;
import pl.jdacewicz.socialmediaserver.reaction.ReactionFacade;
import pl.jdacewicz.socialmediaserver.reaction.ReactionMapper;
import pl.jdacewicz.socialmediaserver.user.UserFacade;
import pl.jdacewicz.socialmediaserver.user.UserMapper;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
class PostService implements PostFacade {

    @Value("${message.post.not-found}")
    private String notFoundPostMessage;

    @Value("${message.post.not-found-visible}")
    private String notFoundVisiblePostMessage;

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserFacade userFacade;
    private final UserMapper userMapper;
    private final ReactionFacade reactionFacade;
    private final ReactionMapper reactionMapper;

    @Override
    public PostDto getPostById(long id) {
        return postRepository.findById(id)
                .map(postMapper::mapToDto)
                .orElseThrow(() -> new PostNotFoundException(notFoundPostMessage));
    }

    @Override
    public PostDto getPostByIdAndVisible(long id, boolean visible) {
        return postRepository.findByIdAndVisible(id, visible)
                .map(postMapper::mapToDto)
                .orElseThrow(() -> new PostNotFoundException(notFoundVisiblePostMessage));
    }

    @Override
    public PostDto createPost(String content, MultipartFile image) throws IOException {
        var loggedUser = userFacade.getLoggedUser();
        var user = userMapper.mapToEntity(loggedUser);
        var post = new Post(content, image.getOriginalFilename(), user);
        var createdPost = postRepository.save(post);
        uploadImage(createdPost.getImageUrl(), image);
        return postMapper.mapToDto(createdPost);
    }

    @Override
    @Transactional
    public void changePostVisibility(long id, boolean visible) {
        postRepository.setVisibleById(id, visible);
    }

    @Override
    public PostDto reactToPost(long postId, int reactionId) {
        var loggedUser = userFacade.getLoggedUser();
        var reaction = reactionFacade.getReactionById(reactionId);
        var post = postRepository.findById(postId)
                        .orElseThrow(() -> new PostNotFoundException(notFoundPostMessage));
        var reactionUser = reactionMapper.mapToReactionUser(reaction, loggedUser);
        post.addReactionUser(reactionUser);
        var reactedPost = postRepository.save(post);
        return postMapper.mapToDto(reactedPost);
    }

    @Override
    public void deletePost(long id) throws IOException {
        var post = getPostById(id);
        deleteDirectory(post.directoryUrl());
        postRepository.deleteById(id);
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
