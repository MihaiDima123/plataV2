package com.plata.Plata.core.configuration;

import com.plata.Plata.core.configuration.filters.JwtAuthenticationServletFilter;
import com.plata.Plata.core.enums.GuildAuthorities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationServletFilter jwtAuthenticationServletFilter;

    public SecurityConfiguration(JwtAuthenticationServletFilter jwtAuthenticationServletFilter) {
        this.jwtAuthenticationServletFilter = jwtAuthenticationServletFilter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
        var builder = http
            .cors(cors -> cors.configure(http))
            .csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(jwtAuthenticationServletFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(authorization -> authorization
                    .requestMatchers("api/v1/auth/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "api/v1/guild")
                        .hasAuthority(GuildAuthorities.CREATE_GUILD.getAuthority())
                    .anyRequest().authenticated()
        );

        return builder.build();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
