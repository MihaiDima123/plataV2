package com.plata.Plata.core.configuration.filters;

import jakarta.annotation.Nonnull;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LocaleContextFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LocaleContextFilter.class);

    @Override
    public void doFilter(@Nonnull ServletRequest servletRequest,
                         @Nonnull ServletResponse servletResponse,
                         @Nonnull FilterChain filterChain) throws IOException, ServletException {
        try {
            LocaleContextHolder.setLocale(servletRequest.getLocale());
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
        } finally {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
