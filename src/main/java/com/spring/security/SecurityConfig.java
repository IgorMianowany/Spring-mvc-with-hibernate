package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource securityDataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(securityDataSource);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        // just for testing purpose
//        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder
//                        .username("john")
//                        .password("test123")
//                        .roles("EMPLOYEE"))
//                .withUser(userBuilder
//                        .username("mary")
//                        .password("test123")
//                        .roles("MANAGER","EMPLOYEE"))
//                .withUser(userBuilder
//                        .username("david")
//                        .password("test123")
//                        .roles("ADMIN", "EMPLOYEE"));
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/customer/showForm*").hasAnyRole("MANAGER", "ADMIN")
                .antMatchers("/customer/save*").hasAnyRole("MANAGER", "ADMIN")
                .antMatchers("/customer/delete").hasRole("ADMIN")
                .antMatchers("/customer/**").hasRole("EMPLOYEE")
                .antMatchers("/resources/**").permitAll()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");

    }

}
