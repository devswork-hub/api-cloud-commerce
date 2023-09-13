package com.devworks.cloudcommerce.module.account.mapper;

import com.devworks.cloudcommerce.module.account.dto.PermissionDTO;
import com.devworks.cloudcommerce.module.account.model.Permission;

public class PermissionMapper {
    private PermissionMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static Permission toEntity(Permission dto) {
        return Permission.builder()
            .id(dto.getId())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())

            .name(dto.getName())
            .active(dto.isActive())
            .build();
    }

    public static PermissionDTO toDto(Permission entity) {
        return PermissionDTO.builder()
            .id(entity.getId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())

            .name(entity.getName())
            .active(entity.isActive())
            .build();
    }
}
