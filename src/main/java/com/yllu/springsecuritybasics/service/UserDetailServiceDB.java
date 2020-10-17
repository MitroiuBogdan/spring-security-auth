package com.yllu.springsecuritybasics.service;

import com.yllu.springsecuritybasics.repo.VaultRepository;
import com.yllu.springsecuritybasics.service.model.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceDB implements UserDetailsService {

    private final VaultRepository vaultRepository;

    public UserDetailServiceDB(VaultRepository vaultRepository) {
        this.vaultRepository = vaultRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return SecurityUser.builder().vault(
                vaultRepository.findVaultByUsername(s)
                        .orElseThrow(() -> new UsernameNotFoundException("NO user fount with name: " + s)))
                .build();
    }
}
