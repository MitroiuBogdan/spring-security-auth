package com.yllu.springsecuritybasics.security.authenication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class OneTimePasswordAuth extends UsernamePasswordAuthenticationToken {


    public OneTimePasswordAuth(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public OneTimePasswordAuth(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
