package com.yllu.springsecuritybasics.security.filter;

import com.yllu.springsecuritybasics.repository.Otp;
import com.yllu.springsecuritybasics.repository.OtpRepository;
import com.yllu.springsecuritybasics.security.TokenManagerInMemory;
import com.yllu.springsecuritybasics.security.authenication.OneTimePasswordAuth;
import com.yllu.springsecuritybasics.security.authenication.UsernamePasswordAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    private TokenManagerInMemory tokenManagerInMemory;


    @Override
    public void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        var username = httpServletRequest.getHeader("username");
        var password = httpServletRequest.getHeader("password");
        var otp = httpServletRequest.getHeader("otp");

        if (otp == null) {
            //Authentication type 1
            Authentication a = new UsernamePasswordAuth(username, password);
            //Manager try to find the appropriate authenticated provider for this type of auth.
            authenticationManager.authenticate(a);

            otpRepository.save(Otp.builder()
                    .username(username)
                    .otp(String.valueOf(new Random().nextInt(9999) + 1000))
                    .build());

        } else {
            //Authentication type 2
            Authentication a = new OneTimePasswordAuth(username, otp);
            authenticationManager.authenticate(a);

            var token = UUID.randomUUID().toString();
            tokenManagerInMemory.add(token);
            httpServletResponse.setHeader("Authorization", token);
        }

    }

    @Override
    public boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
