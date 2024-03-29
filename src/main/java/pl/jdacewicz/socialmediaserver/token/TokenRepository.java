package pl.jdacewicz.socialmediaserver.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByCode(String code);

    @Query("SELECT t FROM Token t " +
            "INNER JOIN User u ON t.user.id = u.id " +
            "WHERE u.id = ?1 " +
            "AND t.expired = false " +
            "AND t.revoked = false ")
    List<Token> findAllValidTokenByUser(long id);
}
