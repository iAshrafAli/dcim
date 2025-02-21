package com.example.dcim.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generates a secure key
	private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

	public String generateToken(String username) {
        logger.info("Generating JWT for user: {}", username); 
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SECRET_KEY).compact();
	}

	public boolean validateToken(String token, String username) {
        try {
        	boolean isValid = extractUsername(token).equals(username) && !isTokenExpired(token);
            logger.info("Token validation status for user {}: {}", username, isValid);
            return isValid;
        } catch (SignatureException e) {
        	logger.error("Token validation failed: {}", e.getMessage());
            throw new RuntimeException("Invalid token signature");
        } catch (MalformedJwtException e) {
        	logger.error("Token validation failed: {}", e.getMessage());
            throw new RuntimeException("Invalid token format");
        } catch (ExpiredJwtException e) {
        	logger.error("Token validation failed: {}", e.getMessage());
            throw new RuntimeException("Token has expired");
        } catch (Exception e) {
        	logger.error("Token validation failed: {}", e.getMessage());
            throw new RuntimeException("Invalid token");
        }
    }

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	private boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
		return claimsResolver.apply(claims);
	}
}
