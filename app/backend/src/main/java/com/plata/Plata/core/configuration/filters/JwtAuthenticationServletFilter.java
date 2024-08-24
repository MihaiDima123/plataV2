package com.plata.Plata.core.configuration.filters;

import com.plata.Plata.core.cookie.HttpOnlyAuthCookie;
import com.plata.Plata.core.exception.ForbiddenException;
import com.plata.Plata.core.jwt.JwtUtils;
import com.plata.Plata.core.threadcontext.UserContext;
import com.plata.Plata.user.repository.UserRepository;
import com.plata.Plata.user.services.PermissionService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationServletFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(LocaleContextFilter.class);

    private final JwtUtils jwtUtils;
    private final com.plata.Plata.core.cookie.Cookie httpOnlyAuthCookie;
    private final UserRepository userRepository;
    private final PermissionService permissionService;

    public JwtAuthenticationServletFilter(JwtUtils jwtUtils,
                                          UserRepository userRepository,
                                          PermissionService permissionService) {
        super();
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.permissionService = permissionService;
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
            var errorMessage = String.format("User %s not found", username);
            var user = userRepository.findByUsername(username).orElseThrow(()-> new ForbiddenException(errorMessage));

            var userGroups = userRepository.getUserGroups(user);

            var authorities = new ArrayList<SimpleGrantedAuthority>();

            userGroups.forEach(group ->
                permissionService.getPermissionGroupPermissionsByPermissionGroupId(group.getId()).forEach(permission ->
                        authorities.add(new SimpleGrantedAuthority(permission.getName()))
                )
            );

            var authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

            UserContext.set(user);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
