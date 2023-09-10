package com.devworks.cloudcommerce.module.account.mapper;

import com.devworks.cloudcommerce.module.account.dto.PermissionDto;
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
            .resourceId(dto.getResourceId())
            .build();
    }

    public static PermissionDto toDto(Permission entity) {
        return PermissionDto.builder()
            .id(entity.getId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())

            .name(entity.getName())
            .resourceId(entity.getResourceId().getId())
            .build();
    }
}
