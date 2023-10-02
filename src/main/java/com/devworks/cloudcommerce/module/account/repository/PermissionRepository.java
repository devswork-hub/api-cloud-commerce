package com.devworks.cloudcommerce.module.account.repository;

import com.devworks.cloudcommerce.module.account.model.Action;
import com.devworks.cloudcommerce.module.account.model.Module;
import com.devworks.cloudcommerce.module.account.model.Permission;
import com.devworks.cloudcommerce.module.account.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    boolean existsByResourceAndAction(Resource resource, Action action);
    List<Permission> findByActionAndResource(Action action, Resource resource);

    @Query("SELECT p.modules FROM Permission p WHERE p = ?1")
    List<Module> findModulesByPermission(Permission permission);
}
