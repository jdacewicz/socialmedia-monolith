package pl.jdacewicz.socialmediaserver.post;

import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.post.dto.PostDto;
import pl.jdacewicz.socialmediaserver.post.dto.PostRequest;
import pl.jdacewicz.socialmediaserver.user.User;


public interface PostMapper {

    PostDto mapToDto(Post post);

    Post mapToPost(PostRequest request, MultipartFile image, User loggedUser);
}
