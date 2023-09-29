package pl.jdacewicz.socialmediaserver.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByIdAndVisible(long id, boolean visible);
}
