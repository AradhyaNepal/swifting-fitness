package com.a2.swifting_fitness.common.config;
//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiZmEzZDNmNi1kZDM4LTRiMjktOWQyNC03YTUzZjU4YWY1YTUiLCJpYXQiOjE3MzI5NjQwNjIsImV4cCI6MTczMjk2NTUwMn0.IpRggJAgrKUvjh0DM14gmhr1ROQZaD847SwIltSs14U


import com.a2.swifting_fitness.common.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/v3/api-docs/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html",
                                        "/api/v1/auth/**",
                                        "/api/v1/refresh",
                                        "/api/v1/open/file/**",
                                        "/assets/**").permitAll()
                                .requestMatchers(
                                        "api/v1/user/**"
                                ).hasRole(UserRole.user.toRoleString())
                                .requestMatchers("api/v1/admin/**")
                                .hasRole(UserRole.admin.toRoleString())
                )
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }


}
