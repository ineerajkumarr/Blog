package com.project.blog.security;

import com.project.blog.model.Users;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final Key key =Jwts.SIG.HS256.key().build();


    public static String generateToken(Users user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", user.getName());
        claims.put("userId", user.getEmail());
        return Jwts.builder()
                .subject(user.getPid())
                .claims(claims)// Set the subject (usually user email)
                .issuedAt(new Date())  // Set the issued date
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  // Set expiration time (1 hour)
                .signWith(key)  // Sign the JWT with the key and algorithm
                .compact();
    }

    public static String extractUserId(String token) {
        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) key) // Replaced setSigningKey
                .build()
                .parseSignedClaims(token) // Replaced parseClaimsJws
                .getPayload(); // Replaced getBody
        return claims.getSubject();
    }
}
