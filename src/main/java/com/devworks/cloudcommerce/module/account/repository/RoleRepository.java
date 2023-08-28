package com.devworks.cloudcommerce.module.account.repository;

import com.devworks.cloudcommerce.module.account.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE LOWER(r.name) = LOWER(?1)")
    Optional<Role> findByName(String name);
}
