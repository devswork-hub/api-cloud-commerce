package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.module.account.dto.input.permission.CreateInput;
import com.devworks.cloudcommerce.module.account.model.Permission;
import com.devworks.cloudcommerce.module.account.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final ActionService actionService;
    private final ResourceService resourceService;

    public PermissionService(
        PermissionRepository permissionRepository,
        ActionService actionService,
        ResourceService resourceService
    ) {
        this.permissionRepository = permissionRepository;
        this.actionService = actionService;
        this.resourceService = resourceService;
    }

    public Permission create(CreateInput input) {
        var resource = resourceService.findById(input.resourceId());
        var action = actionService.findById(input.actionId());

        var existsPermission = permissionRepository.existsByResourceAndAction(resource, action);

        if(existsPermission)
            throw new BadRequestException("Permission already exists");

        var permission = new Permission();
        permission.setResource(resource);
        permission.setAction(action);

        return permissionRepository.save(permission);
    }

    public Permission findById(UUID id) {
        return permissionRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Not found role with id " + id));
    }

    public Set<Permission> getValidPermissionsByUUID(Set<UUID> rolesIds) {
        var validRoles = new HashSet<Permission>();
        for (UUID role : rolesIds) {
            var existsResource = findById(role);
            validRoles.add(existsResource);
        }
        return validRoles;
    }
}
