package com.yllu.springsecuritybasics.security.authenication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class TokenAuth extends UsernamePasswordAuthenticationToken {

    public TokenAuth(Object principal, Object credentials) {
        super(principal, credentials, List.of());
    }

    public TokenAuth(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
