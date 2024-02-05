package com.amazingJava.RestWebService.restWebService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class springSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        //all request should be authenticated
        http.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
        //if a request is failed,then a page is shown for login
        http.httpBasic(withDefaults());
        //CSRF: POST,PUT
        return http.build();
    }
}
