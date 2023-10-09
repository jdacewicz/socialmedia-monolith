package pl.jdacewicz.socialmediaserver.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByIdAndVisible(long id, boolean visible);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE Post p SET p.visible = ?2 WHERE p.id = ?1")
    void setVisibleById(long id, boolean visible);
}
