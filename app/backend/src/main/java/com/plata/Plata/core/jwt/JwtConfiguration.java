package com.plata.Plata.core.jwt;

import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtConfiguration {
    @Nonnull
    private String secret;
    @Nonnull
    private Long expiration;
}
