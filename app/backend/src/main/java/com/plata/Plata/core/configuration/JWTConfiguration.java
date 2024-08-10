package com.plata.Plata.core.configuration;

import com.plata.Plata.core.jwt.JwtConfiguration;
import com.plata.Plata.core.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class JWTConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTConfiguration.class);

    @Value("${app.security.jwt-secret}")
    public String jwtSecret;

    @Value("${app.security.token-expiry:7200000}")
    public Long tokenExpiryInMs;

    @Bean
    public JwtUtils jwtUtils() {
        String secret = jwtSecret;
        if (secret == null) {
            LOGGER.warn("[jwtUtils] - secret was null");
            secret = String.valueOf(new Date().getTime());
        }

        final var configuration = JwtConfiguration
                .builder()
                .secret(secret)
                .expiration(tokenExpiryInMs)
                .build();

        return new JwtUtils(configuration);
    }
}
