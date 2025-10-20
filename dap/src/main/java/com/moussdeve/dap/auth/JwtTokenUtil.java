//*************************************************************************************************************************************************
// *	Title		:	
// * 	Author		:	Armand Moussaouyi
// *	Date		:	Thursday 17th July, 2025
// *	Version		:	v1.0.0

// * 	Description	:	Utitlity class used to generate, validate, and parse JWT tokens (JSON Web Tokens).
// *                    Created to handle token-based authentication in stateless REST APIs (It's not built into Spring).
// *===============================================================================================================================================
// *
// *	Dependencies:   
// *	Usage		:	
// *	Notes		:	
//*************************************************************************************************************************************************

package com.moussdeve.dap.auth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
    // Read jwt values from properties for now
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getSigningKey() {
        // Ensure the secret is at least 32 characters long for HS256
        if (secret.length() < 32){
            throw new IllegalArgumentException("JWT secret must be at least 32 characters long");
        }
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Generate a token when a user logs in
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    // Creates a token
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
            .claims().empty().add(claims).and()
            .subject(subject)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + expiration * 1000))
            .signWith(getSigningKey()/*, SignatureAlgorithm.HS256*/)
            .compact();
    }

    // Extract the username from the token user details
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract the expiration date from the token user details
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        }
        catch (ExpiredJwtException eje) {
            throw new RuntimeException("JWT token expired", eje);
        } catch (MalformedJwtException mje) {
            throw new RuntimeException("Invalid JWT token", mje);
        } catch (Exception e) {
            throw new RuntimeException("JWT token validation failed", e);
        }
    }

    // Check if the token has expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validate token to ensure they authentic and not expired
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Helper method to validate token without userDetails
    public Boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } 
        catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
