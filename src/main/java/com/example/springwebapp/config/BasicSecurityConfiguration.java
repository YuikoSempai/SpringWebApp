package com.example.springwebapp.config;

import com.example.springwebapp.services.UserInfoDetailsServiceImpl;
import com.example.springwebapp.util.RequestMessageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;

@EnableWebSecurity
@Configuration
public class BasicSecurityConfiguration {

    private final UserInfoDetailsServiceImpl userDetailsService;

    @Autowired
    public BasicSecurityConfiguration(UserInfoDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .httpBasic()
                .disable()
                .authorizeHttpRequests((c) -> c
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin((c) -> c
                        .loginProcessingUrl("/auth/login")
                        .successHandler(successHandler())
                        .failureHandler(failureHandler())
                )
                .logout((c) -> c
                        .logoutUrl("/auth/logout")
                        .logoutSuccessHandler(logoutSuccessHandler())
                )
                .exceptionHandling((c) -> c
                        .accessDeniedHandler(accessDeniedHandler())
                        .authenticationEntryPoint(authenticationEntryPoint())
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            Object objToWrite;
            if (authentication.getPrincipal() instanceof UserDetails) {
                objToWrite = ((UserDetails) authentication.getPrincipal()).getAuthorities()
                        .stream().map(GrantedAuthority::getAuthority).toList();
            } else {
                objToWrite = new RequestMessageResponse("user " + authentication.getName() + " successfully authenticated");
            }
            writeJsonResponse(response, HttpStatus.OK.value(), objToWrite);
        };
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return (request, response, exception) ->
                writeJsonResponse(response, HttpStatus.UNAUTHORIZED.value(), new RequestMessageResponse(exception));
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) ->
                writeJsonResponse(response, HttpStatus.OK.value(), new RequestMessageResponse("user " + authentication.getName() + " successfully logged out"));
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) ->
                writeJsonResponse(response, HttpStatus.UNAUTHORIZED.value(), new RequestMessageResponse(accessDeniedException));
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) ->
                writeJsonResponse(response, HttpStatus.UNAUTHORIZED.value(), new RequestMessageResponse(authException));
    }

    private void writeJsonResponse(HttpServletResponse response, int status, Object obj) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        jsonWriter().writeValue(response.getWriter(), obj);
    }

    @Bean
    public ObjectMapper jsonWriter() {
        return new ObjectMapper();
    }
}
