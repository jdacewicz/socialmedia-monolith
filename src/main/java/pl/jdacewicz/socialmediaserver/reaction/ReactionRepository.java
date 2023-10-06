package pl.jdacewicz.socialmediaserver.reaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

interface ReactionRepository extends JpaRepository<Reaction, Integer> {

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE Reaction r " +
            "SET r.name = ?2, r.imageName = ?3 " +
            "WHERE r.id = ?1")
    void setReactionNameAndImageNameById(int id, String reactionName, String imageName);
}
