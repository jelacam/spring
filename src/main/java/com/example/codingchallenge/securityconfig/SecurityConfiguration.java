package com.example.codingchallenge.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .authorizeRequests()
//                .anyRequest()
//                .permitAll()
//                .and()
//                .addFilterBefore(customFilter(), BasicAuthenticationFilter.class)
//                .httpBasic();
//        httpSecurity.csrf().disable();
//    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .anyRequest()
                .permitAll().and().httpBasic();
        httpSecurity.csrf().disable();
    }

//    @Bean
//    public CustomFilter customFilter() {
//        return new CustomFilter();
//    }

//    @Override
//    protected  void configure(AuthenticationManagerBuilder auth) throws  Exception {
//        // use auth.authenticationProvider to get this data from database  ***
//        auth.inMemoryAuthentication()
//                .withUser("marko").password("marko").roles("ADMIN").and()
//                .withUser("user").password("user").roles("ADMIN");
//    }
}
