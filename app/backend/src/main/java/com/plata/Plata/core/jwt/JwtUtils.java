package com.plata.Plata.core.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtils {
    private final JwtConfiguration configuration;
    private final SecretKey signingKey;

    public JwtUtils(JwtConfiguration configuration) {
        this.configuration = configuration;
        this.signingKey = getSigningKey(configuration.getSecret());
    }

    public String generateToken(Authentication authentication) {
        var currentUser = authentication.getPrincipal();
        var expiryDate = new Date(new Date().getTime() + configuration.getExpiration());

        return Jwts
                .builder()
                .subject(currentUser.toString())
                .issuedAt(new Date())
                .signWith(signingKey)
                .expiration(expiryDate)
                .compact();
    }

    public String getSubject(String token) {
        return Jwts
                .parser()
                .decryptWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(signingKey)
                    .build();
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("JWT token not found");
        }
    }

    private SecretKey getSigningKey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}
