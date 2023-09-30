package pl.jdacewicz.socialmediaserver.post;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PostMapper {

    public Post toPost(PostRequest request, MultipartFile file) {
        return new Post(request.content(),
                file.getName());
    }
}
