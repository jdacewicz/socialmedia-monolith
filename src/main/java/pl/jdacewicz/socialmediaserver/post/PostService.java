package pl.jdacewicz.socialmediaserver.post;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
class PostService {

    private final PostRepository postRepository;

    public Post getPostByIdAndVisible(long id, boolean visible) {
        return postRepository.findByIdAndVisible(id, visible)
                .orElseThrow(() -> new PostNotFoundException(""));
    }

    public Post createPost() {
        throw new UnsupportedOperationException();
    }

    @Transactional
    public boolean changePostVisibility(long id, boolean visible) {
        var updatedRecordsCount = postRepository.setVisibleById(id, visible);
        return updatedRecordsCount > 0;
    }

    public void deletePost(long id) throws IOException {
        var postDirectoryUrl = Post.getDirectoryUrl(id);
        var directory = new File(postDirectoryUrl);

        FileUtils.deleteDirectory(directory);
        postRepository.deleteById(id);
    }
}
