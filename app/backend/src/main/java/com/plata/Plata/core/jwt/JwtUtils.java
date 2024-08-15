package com.plata.Plata.core.jwt;

import com.plata.Plata.core.exception.ForbiddenException;
import com.plata.Plata.core.messages.Errors;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
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

    public Claims validateAndGetToken(String token) throws ForbiddenException {
        try {
            var tokenData = Jwts.parser()
                    .verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            if (tokenData.getExpiration().before(new Date())) {
                throw new ForbiddenException();
            }

            return tokenData;
        } catch (Exception e) {
            throw new ForbiddenException(Errors.JWT_DECODE_ISSUE.value());
        }
    }

    private SecretKey getSigningKey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}
