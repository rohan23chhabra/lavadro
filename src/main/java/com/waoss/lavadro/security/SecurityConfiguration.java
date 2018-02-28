package com.waoss.lavadro.security;

import com.waoss.lavadro.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    @Autowired
    public SecurityConfiguration(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/lavadroUI#!loginView").permitAll()
                .antMatchers("/lavadroUI#!homeView", "/lavadroUI#!productsView",
                        "/lavadroUI#!cartView").access("hasRole('USER')")
                .and()
                .formLogin()
                .defaultSuccessUrl("/lavadroUI#!homeView")
                .loginPage("/lavadroUI#!loginView")
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .logout()
                .logoutSuccessUrl("/lavadroUI#!loginView");

    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) {
        userRepository.findAll().forEach(user -> {
            try {
                auth.inMemoryAuthentication().withUser(user.getUsername()).password(user.getPassword()).roles("USER");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}
