package pl.jdacewicz.socialmediaserver.token;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenTest {

    @Test
    void testCheckIsNotRevokedAndNotExpiredTokenActive() {
        //Given
        var token = new Token();
        //When
        var isActive = token.isTokenActive();
        //Then
        assertTrue(isActive);
    }

    @Test
    void testCheckIsNotRevokedAndExpiredTokenActive() {
        //Given
        var token = new Token();
        token.setExpired(true);
        //When
        var isActive = token.isTokenActive();
        //Then
        assertFalse(isActive);
    }

    @Test
    void testCheckIsRevokedAndNotExpiredTokenActive() {
        //Given
        var token = new Token();
        token.setRevoked(true);
        //When
        var isActive = token.isTokenActive();
        //Then
        assertFalse(isActive);
    }

    @Test
    void testCheckIsRevokedAndExpiredTokenActive() {
        //Given
        var token = new Token();
        token.setExpired(true);
        token.setRevoked(true);
        //When
        var isActive = token.isTokenActive();
        //Then
        assertFalse(isActive);
    }
}