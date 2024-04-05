package com.vodafone.deal.SportyShoesPrototype.config;

import com.vodafone.deal.SportyShoesPrototype.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private LoginService service;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security
                .csrf(csrfConfigurer -> csrfConfigurer.disable())
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(new SuccessHandler())
                        .permitAll()
                )
                .authorizeHttpRequests(authorization -> {
                    authorization.requestMatchers("/login/*", "/logout").permitAll();
                    authorization.requestMatchers("/person/*").permitAll();
                    authorization.requestMatchers("/customer", "/customer/*").hasRole("USER");
                    authorization.requestMatchers("/admin", "/admin/*").hasRole("ADMIN");
                })
        ;
        return security.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return service;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(service);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
