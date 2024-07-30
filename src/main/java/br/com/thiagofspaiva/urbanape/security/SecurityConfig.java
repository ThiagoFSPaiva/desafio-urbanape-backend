package br.com.thiagofspaiva.urbanape.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    final private SecurityFilter securityFilter;

    final private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public SecurityConfig(SecurityFilter securityFilter, CustomAuthenticationEntryPoint customAuthenticationEntryPoint){
        this.securityFilter = securityFilter;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                        .requestMatchers(HttpMethod.GET, "/card").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/card/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/card/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/card/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
