package com.plata.Plata.core.cookie;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface Cookie {
    Optional<jakarta.servlet.http.Cookie> getFromServletRequest(@Nonnull HttpServletRequest request);
    jakarta.servlet.http.Cookie createNewCookie(@Nonnull String value);
}
