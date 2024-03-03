package com.amazingJava.RestWebService.restWebService.controllers.basicSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


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
        http.headers().frameOptions().sameOrigin();    //h2 console e frame disable thakle enable kore
        return  http.build();
    }
    //no session, login form nai : cz form remove korechi, logout page nai


    @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        var user =  User.withUsername("jewel")
                //.password("{noop}1234")
                .password("1234")
                .passwordEncoder(str ->passwordEncoder().encode(str))
                .roles("USER")
                .build();

        var admin =  User.withUsername("admin")
                //.password("{noop}1234")
                .password("1213")
                .passwordEncoder(str ->passwordEncoder().encode(str))
                .roles("ADMIN","USER")
                .build();
        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(user);
        jdbcUserDetailsManager.createUser(admin);

        return  jdbcUserDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
