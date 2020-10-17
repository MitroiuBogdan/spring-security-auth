package com.yllu.springsecuritybasics.repo;

import com.yllu.springsecuritybasics.repo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {

    Optional<Users> findVaultByUsername(String username);
}
