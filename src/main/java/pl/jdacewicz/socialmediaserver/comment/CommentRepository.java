package pl.jdacewicz.socialmediaserver.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndVisible(long id, boolean visible);
}
