package com.example.APIRollTheDice.APIRollTheDice_Backend.Security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;


    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateToken(String username,String role, Long userId) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role",role)
                .claim("userId", userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getRoleFromToken(String token) {return getAllClaimsFromToken(token).get("role", String.class);}

    public Long getUserIdFromToken(String token) {
        return getAllClaimsFromToken(token).get("userId", Long.class);
    }


    public boolean validateToken(String token, String username) {
        try {
            String extractedUsername = getUsernameFromToken(token);
            return (extractedUsername.equals(username) && !isTokenExpired(token));
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }


    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    private boolean isTokenExpired(String token) {
        Date expiration = getClaimFromToken(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

}
