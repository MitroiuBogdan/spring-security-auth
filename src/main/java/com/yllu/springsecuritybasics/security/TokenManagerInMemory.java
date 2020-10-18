package com.yllu.springsecuritybasics.security;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TokenManagerInMemory {

    private Set<String> tokens=new HashSet<>();

    public void add(String token) {
        tokens.add(token);
    }
    public boolean contains(String token) {
        return this.tokens.contains(token);
    }

}
