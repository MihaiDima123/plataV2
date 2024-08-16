package com.plata.Plata.core.configuration.filters;

import com.plata.Plata.core.cookie.HttpOnlyAuthCookie;
import com.plata.Plata.core.exception.ForbiddenException;
import com.plata.Plata.core.jwt.JwtUtils;
import com.plata.Plata.core.threadcontext.UserContext;
import com.plata.Plata.user.repository.UserRepository;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationServletFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(LocaleContextFilter.class);

    private final JwtUtils jwtUtils;
    private final com.plata.Plata.core.cookie.Cookie httpOnlyAuthCookie;
    private final UserRepository userRepository;

    public JwtAuthenticationServletFilter(JwtUtils jwtUtils,
                                          UserRepository userRepository) {
        super();
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.httpOnlyAuthCookie = new HttpOnlyAuthCookie();
    }

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {
        try {
            var optionalAuthCookie = httpOnlyAuthCookie.getFromServletRequest(request);
            if (optionalAuthCookie.isPresent()) {
                this.computeSecurityContext(optionalAuthCookie.get());
            }
        } catch (ForbiddenException ignored) {
            // Do nothing
        } catch (Throwable t) {
            logger.error("Unexpected error", t);
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private void computeSecurityContext(Cookie authenticationCookieObject) throws ForbiddenException {
        var authenticationCookie = authenticationCookieObject.getValue();

        String username = null;

        if (authenticationCookie != null && !authenticationCookie.isEmpty()) {
            username = jwtUtils.validateAndGetToken(authenticationCookie).getSubject();
        }

        if (username != null) {
            var authentication = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());

            UserContext.set(userRepository.findByUsername(authentication.getName())
                    .orElseThrow(()->
                            new ForbiddenException(String.format("User %s not found", authentication.getName()))
                    ));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
