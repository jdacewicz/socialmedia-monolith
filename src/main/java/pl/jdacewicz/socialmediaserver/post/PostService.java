package pl.jdacewicz.socialmediaserver.post;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.post.dto.PostDto;
import pl.jdacewicz.socialmediaserver.reaction.ReactionUserFacade;
import pl.jdacewicz.socialmediaserver.user.UserFacade;
import pl.jdacewicz.socialmediaserver.user.UserMapper;

import java.io.IOException;

import static pl.jdacewicz.socialmediaserver.utils.FileUtils.deleteDirectory;
import static pl.jdacewicz.socialmediaserver.utils.FileUtils.uploadFile;

@Service
@RequiredArgsConstructor
class PostService implements PostFacade {

    @Value("${message.post.not-found}")
    private String notFoundPostMessage;

    @Value("${message.post.not-found-visible}")
    private String notFoundVisiblePostMessage;

    private final PostRepository postRepository;

    private final UserFacade userFacade;
    private final ReactionUserFacade reactionUserFacade;

    private final PostMapper postMapper;
    private final UserMapper userMapper;

    @Override
    public PostDto getPostById(long id) {
        return postMapper.mapToDto(getUnmappedPostById(id));
    }

    @Override
    public PostDto getPostByIdAndVisible(long id, boolean visible) {
        return postRepository.findByIdAndVisible(id, visible)
                .map(postMapper::mapToDto)
                .orElseThrow(() -> new PostNotFoundException(notFoundVisiblePostMessage));
    }

    @Override
    public PostDto createPost(String content, MultipartFile image) throws IOException {
        var post = preparePost(content, image.getOriginalFilename());
        var createdPost = postRepository.save(post);
        uploadFile(createdPost.getImageUrl(), image);
        return postMapper.mapToDto(createdPost);
    }

    @Override
    @Transactional
    public void changePostVisibility(long id, boolean visible) {
        postRepository.setVisibleById(id, visible);
    }

    @Override
    public PostDto reactToPost(long postId, int reactionId) {
        var post = getUnmappedPostById(postId);
        var reactionUser = reactionUserFacade.createReactionUser(reactionId);
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

    private Post getUnmappedPostById(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(notFoundPostMessage));
    }

    private Post preparePost(String content, String imageUrl) {
        var loggedUser = userFacade.getLoggedUser();
        var user = userMapper.mapToEntity(loggedUser);
        return new Post(content, imageUrl, user);
    }
}
