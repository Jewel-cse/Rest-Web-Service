package com.amazingJava.RestWebService.restWebService.controllers.basicSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class BasicAuthSecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                auth -> {
                    auth.anyRequest().authenticated();
                });
        http.sessionManagement(
                session ->{session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);}
        );
        http.httpBasic();    //depricatd
        http.csrf().disable();
        return  http.build();
    }
    //no session, login form nai : cz form remove korechi, logout page nai

    @Bean
    public UserDetailsService userDetailsService(){
        var user =  User.withUsername("jewel")
                .password("{noop}1234")
                .roles("USER")
                .build();

        var admin =  User.withUsername("admin")
                .password("{noop}1234")
                .roles("ADMIN")
                .build();
        return  new InMemoryUserDetailsManager(user,admin);
    }

}
