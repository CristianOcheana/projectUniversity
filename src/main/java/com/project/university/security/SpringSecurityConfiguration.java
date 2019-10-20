package com.project.university.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource(name = "userService")
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/index").permitAll()
                .and().authorizeRequests().antMatchers("/static/css/**", "/js/**", "/images/**", "/**/favicon.ico").permitAll()
                .and().authorizeRequests().antMatchers("/login", "logout").permitAll()
                .and().authorizeRequests().antMatchers("/admin").authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
        ;

        http.csrf().disable(); // disable CRSF
        http.headers().frameOptions().disable(); // H2 database console runs inside a frame, So we need to disable X-Frame-Options in Spring Security
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/*.css");
        web.ignoring().antMatchers("/*.js");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService);
    }
}
