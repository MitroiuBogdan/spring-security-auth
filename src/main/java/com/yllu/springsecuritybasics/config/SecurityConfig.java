package com.yllu.springsecuritybasics.config;


import com.yllu.springsecuritybasics.security.filter.TokenAuthFilter;
import com.yllu.springsecuritybasics.security.filter.UsernamePasswordAuthFilter;
import com.yllu.springsecuritybasics.security.provider.OneTimePasswordAuthProvider;
import com.yllu.springsecuritybasics.security.provider.TokenAuthProvider;
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
    private TokenAuthProvider tokenAuthProvider;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UsernamePasswordAuthFilter getUsernamePasswordAuthFilter() {
        return new UsernamePasswordAuthFilter();
    }

    @Bean
    public TokenAuthFilter getTokenAuthFilter() {
        return new TokenAuthFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(authProvider)
                .authenticationProvider(oneTimePasswordAuthProvider)
                .authenticationProvider(tokenAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterAt(getUsernamePasswordAuthFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(getTokenAuthFilter(), BasicAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}


