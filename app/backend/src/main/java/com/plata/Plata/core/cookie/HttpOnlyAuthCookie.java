package com.plata.Plata.core.cookie;

import jakarta.annotation.Nonnull;

public class HttpOnlyAuthCookie extends AbstractHttpOnlyCookie {
    public static final String JWT_AUTH_HTTP_ONLY_COOKIE = "jwt-token";

    @Nonnull
    @Override
    String getName() {
        return JWT_AUTH_HTTP_ONLY_COOKIE;
    }
}
