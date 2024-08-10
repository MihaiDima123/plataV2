package com.plata.Plata.core.configuration;

import com.plata.Plata.core.configuration.filters.JwtAuthenticationServletFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationServletFilter jwtAuthenticationServletFilter;

    public SecurityConfiguration(JwtAuthenticationServletFilter jwtAuthenticationServletFilter) {
        this.jwtAuthenticationServletFilter = jwtAuthenticationServletFilter;
    }

    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
        var builder = http
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(jwtAuthenticationServletFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(authorization -> authorization
                    .requestMatchers("api/v1/auth/**").permitAll()
                    .anyRequest()   .authenticated()
        );

        return builder.build();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
