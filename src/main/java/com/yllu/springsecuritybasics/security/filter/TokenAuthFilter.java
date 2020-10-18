package com.yllu.springsecuritybasics.security.filter;

import com.yllu.springsecuritybasics.security.TokenManagerInMemory;
import com.yllu.springsecuritybasics.security.authenication.TokenAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TokenAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        var token = httpServletRequest.getHeader("Authorization");

        var a = authenticationManager.authenticate(new TokenAuth(token, null));

        SecurityContextHolder.getContext().setAuthentication(a);
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login");
    }
}
