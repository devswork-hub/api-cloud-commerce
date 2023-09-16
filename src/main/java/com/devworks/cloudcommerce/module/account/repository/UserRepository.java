package com.devworks.cloudcommerce.module.account.repository;

import com.devworks.cloudcommerce.module.account.constants.UserType;
import com.devworks.cloudcommerce.module.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    List<User> findByUserType(UserType userType);
}
