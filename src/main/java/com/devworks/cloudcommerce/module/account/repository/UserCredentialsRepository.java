package com.devworks.cloudcommerce.module.account.repository;

import com.devworks.cloudcommerce.module.account.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
    Optional<UserCredentials> findByEmail(String email);
}
