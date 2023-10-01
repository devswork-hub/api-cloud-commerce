package com.devworks.cloudcommerce.module.account.mapper;

import com.devworks.cloudcommerce.module.account.dto.PermissionDTO;
import com.devworks.cloudcommerce.module.account.model.*;
import com.devworks.cloudcommerce.module.account.model.Module;

import java.util.Set;

public class PermissionMapper {
    private PermissionMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static Permission toEntity(
        PermissionDTO dto,
        Action action,
        Resource resource,
        Set<Module> modules
    ) {
        return Permission.builder()
            .id(dto.getId())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())
            .action(action)
            .resource(resource)
            .modules(modules)
            .build();
    }

    public static PermissionDTO toDto(Permission entity) {
        return PermissionDTO.builder()
            .id(entity.getId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .actionId(entity.getAction().getId())
            .resourceId(entity.getResource().getId())
            .modules(entity.getModules())
            .build();
    }
}
