package com.yllu.springsecuritybasics.security.provider;

import com.yllu.springsecuritybasics.repository.OtpRepository;
import com.yllu.springsecuritybasics.security.authenication.OneTimePasswordAuth;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OneTimePasswordAuthProvider implements AuthenticationProvider {


    private final OtpRepository otpRepository;

    public OneTimePasswordAuthProvider(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String otp = (String) authentication.getCredentials();

        if (otpRepository.findOneTimePasswordByUsername(username).isPresent()) {
            return new OneTimePasswordAuth(username, otp, List.of(()->"read"));
        }
        throw new BadCredentialsException("Bad user or password");

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OneTimePasswordAuth.class.equals(aClass);
    }
}
