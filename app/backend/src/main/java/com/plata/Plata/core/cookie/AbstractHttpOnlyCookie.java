package com.plata.Plata.core.cookie;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

abstract class AbstractHttpOnlyCookie implements com.plata.Plata.core.cookie.Cookie {
    @Nonnull
    abstract String getName();

    protected Cookie createNewCookieHandle(@Nonnull String value) {
        return new Cookie(getName(), value);
    };

    public Optional<Cookie> getFromServletRequest(@Nonnull HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }

        return Arrays.stream(request.getCookies())
                .filter(it -> it.getName().equals(getName()))
                .findFirst();
    }

    public jakarta.servlet.http.Cookie createNewCookie(@Nonnull String value) {
        var cookie = createNewCookieHandle(value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");

        return cookie;
    }
}
