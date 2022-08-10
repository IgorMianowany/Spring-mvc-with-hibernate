package com.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // just for testing purpose
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(userBuilder
                        .username("john")
                        .password("test123")
                        .roles("EMPLOYEE"))
                .withUser(userBuilder
                        .username("mary")
                        .password("test123")
                        .roles("MANAGER","EMPLOYEE"))
                .withUser(userBuilder
                        .username("susan")
                        .password("test123")
                        .roles("ADMIN", "EMPLOYEE"));

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers("/customer/leaders/**").hasRole("MANAGER")
                .antMatchers("/customer/systems/**").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();

    }

}
