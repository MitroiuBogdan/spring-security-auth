package com.yllu.springsecuritybasics.service;

import com.yllu.springsecuritybasics.repo.UsersRepository;
import com.yllu.springsecuritybasics.service.model.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//@Component
public class UserDetailServiceDB  {
//
//    private final UsersRepository usersRepository;
//
//    public UserDetailServiceDB(UsersRepository usersRepository) {
//        this.usersRepository = usersRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return SecurityUser.builder().users(
//                usersRepository.findVaultByUsername(s)
//                        .orElseThrow(() -> new UsernameNotFoundException("NO user fount with name: " + s)))
//                .build();
//    }
}
