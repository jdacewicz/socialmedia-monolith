package pl.jdacewicz.socialmediaserver.post;

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
class PostService implements PostFacade {

    @Value("${message.post.not-found}")
    private String notFoundPostMessage;

    @Value("${message.post.not-found-visible}")
    private String notFoundVisiblePostMessage;

    private final PostRepository postRepository;

    @Override
    public Post getPostById(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(notFoundPostMessage));
    }

    @Override
    public Post getPostByIdAndVisible(long id, boolean visible) {
        return postRepository.findByIdAndVisible(id, visible)
                .orElseThrow(() -> new PostNotFoundException(notFoundVisiblePostMessage));
    }

    @Override
    public Post createPost(Post post, MultipartFile image) throws IOException {
        var createdPost = postRepository.save(post);
        var directory = new File(post.getImageUrl());

        FileUtils.copyInputStreamToFile(image.getInputStream(), directory);
        return createdPost;
    }

    @Override
    @Transactional
    public boolean changePostVisibility(long id, boolean visible) {
        var updatedRecordsCount = postRepository.setVisibleById(id, visible);
        return (updatedRecordsCount > 0);
    }

    @Override
    public void deletePost(long id) throws IOException {
        var directory = new File(getPostById(id)
                .getDirectoryUrl());

        FileUtils.deleteDirectory(directory);
        postRepository.deleteById(id);
    }
}
