package com.plata.Plata.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        var builder = http.authorizeHttpRequests(authorization -> {
            authorization
                    .requestMatchers("/public/**")
                    .permitAll();
        })
        .oauth2ResourceServer((oauth2) -> oauth2.jwt(withDefaults()))
        .cors(AbstractHttpConfigurer::disable);

        return builder.build();
    }
}
