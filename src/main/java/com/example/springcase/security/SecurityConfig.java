package com.example.springcase.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // API için CSRF kapalı
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()          // Açık endpointler
                .requestMatchers("/api/admin/**").hasRole("SUPERADMIN") // Sadece SuperAdmin yetkisi
                .requestMatchers("/api/teacher/**").hasRole("TEACHER")  // Teacher yetkisi
                .requestMatchers("/api/student/**").hasRole("STUDENT")  // Student yetkisi
                .requestMatchers(HttpMethod.GET, "/api/users/**").hasRole("SUPERADMIN")
                .requestMatchers(HttpMethod.POST, "/api/users/**").hasRole("SUPERADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("SUPERADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("SUPERADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}