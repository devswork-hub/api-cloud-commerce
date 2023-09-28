package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.model.RoleResource;
import com.devworks.cloudcommerce.module.account.repository.RoleResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleResourceService {
    private final RoleResourceRepository roleResourceRepository;

    public RoleResourceService(RoleResourceRepository roleResourceRepository) {
        this.roleResourceRepository = roleResourceRepository;
    }

    public List<RoleResource> findRoleResourcesByRole(Role role) {
        return roleResourceRepository.findByRole(role);
    }
}
