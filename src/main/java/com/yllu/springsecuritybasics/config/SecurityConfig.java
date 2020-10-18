package com.yllu.springsecuritybasics.config;


import com.yllu.springsecuritybasics.security.filter.UsernamePasswordAuthFilter;
import com.yllu.springsecuritybasics.security.provider.OneTimePasswordAuthProvider;
import com.yllu.springsecuritybasics.security.provider.UsernamePasswordAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsernamePasswordAuthProvider authProvider;

    @Autowired
    private OneTimePasswordAuthProvider oneTimePasswordAuthProvider;

    @Autowired
    private UsernamePasswordAuthFilter usernamePasswordAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider)
                .authenticationProvider(oneTimePasswordAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(usernamePasswordAuthFilter,
                BasicAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}


