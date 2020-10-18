package com.yllu.springsecuritybasics.services;

import com.yllu.springsecuritybasics.repository.User;
import com.yllu.springsecuritybasics.repository.UserRepository;
import com.yllu.springsecuritybasics.security.model.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceJPA implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceJPA(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userRepository.findUserByUsername(s).orElseThrow();
        return SecurityUser.builder()
                .user(user)
                .build();
    }
}
