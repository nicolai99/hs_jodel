package com.example.jodel.authentification;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // private final JwtConverter jwtConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        // ist notwendig für H2 console, die ist sonst nicht aufrufbar
        http.headers()
                .frameOptions().disable();

        // nur API-Endpunkte benötigen Auth, alles andere erlauben (inkl. static/index)
        http.authorizeHttpRequests((authz) -> authz
                .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/jodel/api/**").authenticated()
                .anyRequest().permitAll());

        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));
        // jwt -> jwt.jwtAuthenticationConverter(jwtConverter))

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();
    }

}
