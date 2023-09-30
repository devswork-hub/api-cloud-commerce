package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.module.account.dto.input.permission.AssignPermissionsInput;
import com.devworks.cloudcommerce.module.account.dto.input.permission.CreateInput;
import com.devworks.cloudcommerce.module.account.mapper.RoleMapper;
import com.devworks.cloudcommerce.module.account.model.Action;
import com.devworks.cloudcommerce.module.account.model.Resource;
import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.model.Permission;
import com.devworks.cloudcommerce.module.account.repository.PermissionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;


@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final ActionService actionService;
    private final ResourceService resourceService;
    private final RoleService roleService;

    public PermissionService(PermissionRepository permissionRepository, ActionService actionService, ResourceService resourceService, RoleService roleService) {
        this.permissionRepository = permissionRepository;
        this.actionService = actionService;
        this.resourceService = resourceService;
        this.roleService = roleService;
    }

    public Permission create(CreateInput input) {
        var role = roleService.findById(input.roleId());
        var resource = resourceService.findById(input.resourceId());
        var action = actionService.findById(input.actionId());

        var permission = new Permission();
        permission.setRole(RoleMapper.toEntity(role));
        permission.setResource(resource);
        permission.setAction(action);

        return permissionRepository.save(permission);
    }


    @Transactional
    public void assignPermissions(UUID roleUUID, UUID resourceUUID, AssignPermissionsInput input) {
        var role = roleService.findById(roleUUID);
        var resource = resourceService.findById(resourceUUID);
        var validActions =  actionService.getValidActions(input.actions());

        var associations = findPermissions(RoleMapper.toEntity(role), resource);

        if (associations.isEmpty()) {
            var permissions = new HashSet<Permission>();

            for (Action action : validActions) {
                var permission = new Permission();
                permission.setRole(RoleMapper.toEntity(role));
                permission.setResource(resource);
                permission.setAction(action);

                permissions.add(permission);
            }
            permissionRepository.saveAll(permissions);
        } else {
            throw new BadRequestException("Permissions already assigned");
        }

    }

    public List<Permission> findPermissions(Role role, Resource resource) {
        return permissionRepository.findByRoleAndResource(role, resource);
    }
}
