package pl.jdacewicz.socialmediaserver.comment;

import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmediaserver.comment.dto.CommentDto;
import pl.jdacewicz.socialmediaserver.post.Post;
import pl.jdacewicz.socialmediaserver.user.User;

import java.util.List;


public interface CommentMapper {

    List<CommentDto> mapToDto(List<Comment> comments);

    CommentDto mapToDto(Comment comment);

    Comment mapToComment(String content, MultipartFile image, User loggedUser, Post post);
}
