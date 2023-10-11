package pl.jdacewicz.socialmediaserver.reaction;

import org.junit.jupiter.api.Test;
import pl.jdacewicz.socialmediaserver.user.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReactionUserTest {

    @Test
    void testCheckIsUserNotUniqueWithUniqueUsers() {
        //Given
        var uniqueReactionUser = new ReactionUser(new Reaction(),
                new User(1, "email", "firstname", "lastname"));
        var diffrentReactionUser = new ReactionUser(new Reaction(),
                new User(2, "email", "firstname", "lastname"));
        var reactionUsers = List.of(diffrentReactionUser);
        //When
        var result = uniqueReactionUser.isUserNotUnique(reactionUsers);
        //Then
        assertFalse(result);
    }

    @Test
    void testCheckIsUserNotUniqueWithNotUniqueUsers() {
        //Given
        var notUniqueReactionUser = new ReactionUser(new Reaction(),
                new User(1, "email", "firstname", "lastname"));
        var reactionUsers = List.of(notUniqueReactionUser);
        //When
        var result = notUniqueReactionUser.isUserNotUnique(reactionUsers);
        //Then
        assertTrue(result);
    }

    @Test
    void testCheckIsUserNotUniqueWithEmptyList() {
        //Given
        var uniqueReactionUser = new ReactionUser(new Reaction(),
                new User(1, "email", "firstname", "lastname"));
        List<ReactionUser> reactionUsers = List.of();
        //When
        var result = uniqueReactionUser.isUserNotUnique(reactionUsers);
        //Then
        assertFalse(result);
    }


    @Test
    void testCheckIsNullUserNotUniqueWithUniqueUsers() {
        //Given
        var nullReactionUser = new ReactionUser(new Reaction(), null);
        var reactionUser = new ReactionUser(new Reaction(),
                new User(1, "email", "firstname", "lastname"));
        var reactionUsers = List.of(reactionUser);
        //When
        var result = nullReactionUser.isUserNotUnique(reactionUsers);
        //Then
        assertFalse(result);
    }

    @Test
    void testCountReactionOccurrencesWithRepeatingReactions() {
        //Given
        var notUniqueReaction = new Reaction();
        var reactionUsers = List.of(new ReactionUser(notUniqueReaction, null),
                new ReactionUser(notUniqueReaction, null));
        //When
        var occurrences = ReactionUser.countOccurrences(reactionUsers);
        //Then
        assertEquals(occurrences.get(notUniqueReaction), 2);
    }

    @Test
    void testCountReactionOccurrencesWithNotRepeatingReactions() {
        //Given
        var uniqueReaction = new Reaction();
        var reactionUsers = List.of(new ReactionUser(uniqueReaction, null),
                new ReactionUser(new Reaction(), null));
        //When
        var occurrences = ReactionUser.countOccurrences(reactionUsers);
        //Then
        assertEquals(occurrences.get(uniqueReaction), 1);
    }

    @Test
    void testCountReactionOccurrencesWithEmptyList() {
        //Given
        List<ReactionUser> reactionUsers = List.of();
        //When
        var occurrences = ReactionUser.countOccurrences(reactionUsers);
        //Then
        assertEquals(occurrences.size(), 0);
    }
}