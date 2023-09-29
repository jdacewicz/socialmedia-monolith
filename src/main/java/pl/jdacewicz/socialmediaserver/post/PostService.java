package pl.jdacewicz.socialmediaserver.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public boolean changePostVisibility(long id, boolean visible) {
        throw new UnsupportedOperationException();
    }

    public boolean deletePost(long id) {
        throw new UnsupportedOperationException();
    }
}
