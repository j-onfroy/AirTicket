package com.company.config;

import com.company.controller.AuthController;
import com.company.controller.CabinetController;
import com.company.utils.AppConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests()
                .requestMatchers(AuthController.BASE_PATH + "/**").permitAll()
                .requestMatchers(AppConstants.APP_BASE_PATH).authenticated()
                .anyRequest().permitAll();
        httpSecurity.formLogin()
                .usernameParameter("email")
               // .loginPage(AuthController.LOGIN_PATH)
               // .loginProcessingUrl(AuthController.LOGIN_PATH)
                .defaultSuccessUrl(CabinetController.BASE_PATH, true);
       // httpSecurity.rememberMe().alwaysRemember(true);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        //userdetailsservice
        //passwordencoder
        return authenticationConfiguration.getAuthenticationManager();
    }
}
