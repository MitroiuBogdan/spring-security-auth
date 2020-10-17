package com.yllu.springsecuritybasics.repo;

import com.yllu.springsecuritybasics.repo.entity.Vault;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VaultRepository extends JpaRepository<Vault, UUID> {

    Optional<Vault> findVaultByUsername(String username);
}
