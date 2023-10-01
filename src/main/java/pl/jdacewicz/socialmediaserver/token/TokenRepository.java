package pl.jdacewicz.socialmediaserver.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByToken(String token);

    @Query("SELECT t FROM Token t " +
            "INNER JOIN User u ON t.user.id = u.id " +
            "WHERE u.id = ?1 " +
            "AND t.expired = false " +
            "AND t.revoked = false ")
    List<Token> findAllValidTokenByUser(Integer id);
}
