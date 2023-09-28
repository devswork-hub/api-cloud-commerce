package com.devworks.cloudcommerce.module.account.repository;

import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.model.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleResourceRepository extends JpaRepository<RoleResource, UUID> {
    List<RoleResource> findByRole(Role role);
}
