package com.devworks.cloudcommerce.module.account.repository;

import com.devworks.cloudcommerce.module.account.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    boolean existsByName(String name);
    Optional<Department> findByName(String name);
}
