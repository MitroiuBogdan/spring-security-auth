package com.yllu.springsecuritybasics.security.provider;

import com.yllu.springsecuritybasics.security.authenication.UsernamePasswordAuth;
import com.yllu.springsecuritybasics.services.UserDetailServiceJPA;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

    private final UserDetailServiceJPA userDetailServiceJPA;
    private final PasswordEncoder passwordEncoder;

    public UsernamePasswordAuthProvider(UserDetailServiceJPA userDetailServiceJPA, PasswordEncoder passwordEncoder) {
        this.userDetailServiceJPA = userDetailServiceJPA;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        //Get the UD form Database
        UserDetails userDetails = userDetailServiceJPA.loadUserByUsername(username);

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuth(username, password, userDetails.getAuthorities());
        }
        throw new BadCredentialsException("Incorrect Username or password");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuth.class.equals(aClass);
    }
}
