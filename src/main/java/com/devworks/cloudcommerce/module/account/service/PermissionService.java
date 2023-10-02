package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.module.account.dto.input.permission.CreateInput;
import com.devworks.cloudcommerce.module.account.model.Permission;
import com.devworks.cloudcommerce.module.account.repository.PermissionRepository;
import org.springframework.stereotype.Service;

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
}
