package pl.jdacewicz.socialmediaserver.comment;

import org.junit.jupiter.api.Test;
import pl.jdacewicz.socialmediaserver.post.Post;
import pl.jdacewicz.socialmediaserver.reaction.ReactionUser;
import pl.jdacewicz.socialmediaserver.user.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @Test
    void testAddReactionUserWithUniqueUser() {
        //Given
        var reactionUser = new ReactionUser(null,
                new User(1, "email", "firstname", "lastname"));
        var comment = new Comment();
        //When
        comment.addReactionUser(reactionUser);
        //Then
        assertEquals(1, comment.getReactionUsers().size());
    }

    @Test
    void testAddReactionUserWithNotUniqueUser() {
        //Given
        var notUniqueUser = new User(1, "email", "firstname", "lastname");
        var reactionUser = new ReactionUser(null, notUniqueUser);
        var comment = new Comment();
        comment.setReactionUsers(List.of(new ReactionUser(null, notUniqueUser)));
        //When
        //Then
        assertThrows(UnsupportedOperationException.class,
                () -> comment.addReactionUser(reactionUser));
    }
}