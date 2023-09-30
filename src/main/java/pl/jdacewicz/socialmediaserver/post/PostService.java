package pl.jdacewicz.socialmediaserver.post;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public Post getPostById(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(""));
    }

    public Post getPostByIdAndVisible(long id, boolean visible) {
        return postRepository.findByIdAndVisible(id, visible)
                .orElseThrow(() -> new PostNotFoundException(""));
    }

    public Post createPost(PostRequest request, MultipartFile file) throws IOException {
        var post = postMapper.toPost(request, file);
        var createdPost = postRepository.save(post);
        var directory = new File(post.getImageUrl());

        FileUtils.copyInputStreamToFile(file.getInputStream(), directory);
        return createdPost;
    }

    @Transactional
    public boolean changePostVisibility(long id, boolean visible) {
        var updatedRecordsCount = postRepository.setVisibleById(id, visible);
        return (updatedRecordsCount > 0);
    }

    public void deletePost(long id) throws IOException {
        var directory = new File(getPostById(id)
                .getDirectoryUrl());

        FileUtils.deleteDirectory(directory);
        postRepository.deleteById(id);
    }
}
