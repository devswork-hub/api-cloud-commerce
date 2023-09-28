package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.module.account.dto.input.RoleResourceAssignInput;
import com.devworks.cloudcommerce.module.account.mapper.RoleMapper;
import com.devworks.cloudcommerce.module.account.model.Resource;
import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.model.RoleResource;
import com.devworks.cloudcommerce.module.account.repository.RoleResourceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;


@Service
public class RoleResourceService {
    private final RoleResourceRepository roleResourceRepository;
    private final ResourceService resourceService;
    private final RoleService roleService;

    public RoleResourceService(RoleResourceRepository roleResourceRepository, ResourceService resourceService, RoleService roleService) {
        this.roleResourceRepository = roleResourceRepository;
        this.resourceService = resourceService;
        this.roleService = roleService;
    }

    @Transactional
    public void assignResourceToRole(UUID roleUUID, RoleResourceAssignInput input) {
        var role = roleService.findById(roleUUID);

        var validResources = resourceService.getValidResourcesIds(input.resourcesIds());

        // Remove existing role-resource associations
        List<RoleResource> existingAssociations = roleResourceRepository.findByRole(RoleMapper.toEntity(role));
        roleResourceRepository.deleteAll(existingAssociations);

        for (Resource resource : validResources) {
            RoleResource roleResourceAssociation = new RoleResource();
            roleResourceAssociation.setResource(resource);
            roleResourceAssociation.setRole(RoleMapper.toEntity(role));
            roleResourceRepository.save(roleResourceAssociation);
        }
    }

    public List<RoleResource> findRoleResourcesByRole(Role role) {
        return roleResourceRepository.findByRole(role);
    }
}
