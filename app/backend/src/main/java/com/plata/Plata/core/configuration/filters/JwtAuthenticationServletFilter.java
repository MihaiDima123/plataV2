package com.plata.Plata.core.configuration.filters;

import com.plata.Plata.core.cookie.HttpOnlyAuthCookie;
import com.plata.Plata.core.exception.ForbiddenException;
import com.plata.Plata.core.jwt.JwtUtils;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Collections;

@Component
public class JwtAuthenticationServletFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final com.plata.Plata.core.cookie.Cookie httpOnlyAuthCookie;

    public JwtAuthenticationServletFilter(JwtUtils jwtUtils) {
        super();
        this.jwtUtils = jwtUtils;
        this.httpOnlyAuthCookie = new HttpOnlyAuthCookie();
    }

    @Override
    @SneakyThrows
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) {
        var optionalAuthCookie = httpOnlyAuthCookie.getFromServletRequest(request);
        if (optionalAuthCookie.isPresent()) {
            this.computeSecurityContext(optionalAuthCookie.get());
        }

        filterChain.doFilter(request, response);
    }

    private void computeSecurityContext(Cookie authenticationCookieObject) throws ForbiddenException {
        var authenticationCookie = authenticationCookieObject.getValue();

        String username = null;

        if (authenticationCookie.startsWith("Bearer ")) {
            var jwtToken = authenticationCookie.substring(7);
            var tokenData = jwtUtils.validateToken(jwtToken);

            username = tokenData.getSubject();
        }

        if (username != null) {
            var authentication = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
