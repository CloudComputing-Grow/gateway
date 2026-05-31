package com.grow.gateway.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    private final SecretKey secretKey;

    public JwtUtil(
            @Value("${jwt.secret}") String secret
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims validateToken(String token) {

        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();

        return parser.parseClaimsJws(token)
                .getBody();
    }
}