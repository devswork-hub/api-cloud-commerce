package com.devworks.cloudcommerce.module.account.service.util;

import com.devworks.cloudcommerce.common.model.BaseServiceUtil;
import com.devworks.cloudcommerce.module.account.mapper.RoleMapper;
import com.devworks.cloudcommerce.module.account.repository.RoleRepository;
import com.devworks.cloudcommerce.module.account.service.PermissionService;
import com.devworks.cloudcommerce.module.account.service.RoleService;

import java.util.Set;
import java.util.UUID;

public class RoleServiceUtil extends BaseServiceUtil {
    private RoleServiceUtil() { super(); }

    public static void assignPermissions(
        UUID roleId,
        Set<UUID> permissionsIds,
        RoleRepository roleRepository,
        RoleService roleService,
        PermissionService permissionService
    ) {
        var existsRole = roleService.findById(roleId);
        var validPermissions = permissionService.getValidPermissionsByUUID(permissionsIds);

        existsRole.setPermissions(validPermissions);
        roleRepository.save(RoleMapper.toEntity(existsRole));
    }
}
