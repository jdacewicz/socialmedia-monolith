package pl.jdacewicz.socialmediaserver.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndVisible(long id, boolean visible);

    @Query("SELECT c FROM Comment c " +
            "INNER JOIN Post p ON c.post.id = p.id " +
            "WHERE p.id = ?1")
    List<Comment> findAllByPostId(long id);
}
