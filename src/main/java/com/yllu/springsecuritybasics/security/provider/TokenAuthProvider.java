package com.yllu.springsecuritybasics.security.provider;

import com.yllu.springsecuritybasics.security.TokenManagerInMemory;
import com.yllu.springsecuritybasics.security.authenication.TokenAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TokenAuthProvider implements AuthenticationProvider {

    @Autowired
    private TokenManagerInMemory tokenManagerInMemory;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String token = authentication.getName();

        if (tokenManagerInMemory.contains(token)) {
            return new TokenAuth(token, null, List.of());
        }
        throw new BadCredentialsException("Incorrect Token");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TokenAuth.class.equals(aClass);
    }
}
