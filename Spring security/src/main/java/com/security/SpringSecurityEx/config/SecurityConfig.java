package com.security.SpringSecurityEx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// This class is used to configure Spring Security
// It is annotated with @Configuration to indicate that it is a Spring configuration class
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    
    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean // Marks this method as a Spring bean, meaning Spring will manage its lifecycle
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    
        // Disables CSRF protection. Useful for stateless APIs but should be used with caution.
        http.csrf(customizer -> customizer.disable()); 
        
        // Requires authentication for all HTTP requests. No public endpoints are allowed.
        // http.authorizeHttpRequests(request -> request.anyRequest().authenticated()); 
        
        // Configura as regras de autorização
        http.authorizeHttpRequests(request -> request
            .requestMatchers("/register").permitAll() // Permite acesso ao endpoint /register sem autenticação
            .anyRequest().authenticated() // Requer autenticação para qualquer outro endpoint
        );
        
        // Enables form-based login with default settings. 
        // If removed, the browser will trigger a basic authentication popup.
        http.formLogin(Customizer.withDefaults());
        
        // Enables HTTP Basic authentication, allowing clients to authenticate via an Authorization header.
        http.httpBasic(Customizer.withDefaults());
        
        // Configures session management to be stateless. 
        // This is ideal for APIs where each request should be authenticated independently.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    
        // Builds and returns the configured SecurityFilterChain.
        return http.build();
    }

    //AuthenticationProvider is an interface that provides authentication logic
    //DaoAuthenticationProvider is an implementation of AuthenticationProvider
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // Sets the custom UserDetailsService to be used by the DaoAuthenticationProvider
        // provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        // Sets the custom UserDetailsService to be used by the DaoAuthenticationProvider
        // But this time, we are using a BCryptPasswordEncoder to encrypt passwords
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    // @Bean
    // public UserDetailsService userDetailsService(){

    //     UserDetails user1 = User.withDefaultPasswordEncoder()
    //         .username("rafa")
    //         .password("123456")
    //         .roles("USER")
    //         .build();

    //     return new InMemoryUserDetailsManager(user1);
    // }

}

