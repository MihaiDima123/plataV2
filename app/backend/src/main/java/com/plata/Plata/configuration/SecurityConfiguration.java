package com.plata.Plata.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
        var builder = http.authorizeHttpRequests(authorization ->
            authorization
                    .anyRequest()
                    .authenticated()
        )
        .cors(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable);

        return builder.build();
    }
}
