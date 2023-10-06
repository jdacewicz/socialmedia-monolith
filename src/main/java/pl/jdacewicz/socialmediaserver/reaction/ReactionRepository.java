package pl.jdacewicz.socialmediaserver.reaction;

import org.springframework.data.jpa.repository.JpaRepository;

interface ReactionRepository extends JpaRepository<Reaction, Integer> {
}
