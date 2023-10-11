package pl.jdacewicz.socialmediaserver.post;

import org.junit.jupiter.api.Test;
import pl.jdacewicz.socialmediaserver.reaction.ReactionUser;
import pl.jdacewicz.socialmediaserver.user.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostTest {

    @Test
    void testAddReactionUserWithUniqueUser() {
        //Given
        var reactionUser = new ReactionUser(null,
                new User(1, "email", "firstname", "lastname"));
        var post = new Post();
        //When
        post.addReactionUser(reactionUser);
        //Then
        assertEquals(1, post.getReactionUsers().size());
    }

    @Test
    void testAddReactionUserWithNotUniqueUser() {
        //Given
        var notUniqueUser = new User(1, "email", "firstname", "lastname");
        var reactionUser = new ReactionUser(null, notUniqueUser);
        var post = new Post();
        post.setReactionUsers(List.of(new ReactionUser(null, notUniqueUser)));
        //When
        //Then
        assertThrows(UnsupportedOperationException.class,
                () -> post.addReactionUser(reactionUser));
    }
}