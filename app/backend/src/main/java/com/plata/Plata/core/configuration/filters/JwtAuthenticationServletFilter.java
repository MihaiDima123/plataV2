package com.plata.Plata.core.configuration.filters;

import com.plata.Plata.core.jwt.JwtUtils;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
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

    public JwtAuthenticationServletFilter(JwtUtils jwtUtils) {
        super();
        this.jwtUtils = jwtUtils;
    }

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) {
        var authenticationHeader = request.getHeader("Authorization");

        String username = null;

        if (authenticationHeader != null && authenticationHeader.startsWith("Bearer ")) {
            var jwtToken = authenticationHeader.substring(7);
            var tokenData = jwtUtils.validateToken(jwtToken);

            username = tokenData.getSubject();
        }

        if (username != null) {
            var authentication = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
