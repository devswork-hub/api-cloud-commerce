package com.devworks.cloudcommerce.module.account.mapper;

import com.devworks.cloudcommerce.module.account.dto.RoleDTO;
import com.devworks.cloudcommerce.module.account.model.Role;

public class RoleMapper {
    private RoleMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static Role toEntity(RoleDTO dto) {
      return Role.builder()
        .id(dto.getId())
        .createdAt(dto.getCreatedAt())
        .updatedAt(dto.getUpdatedAt())

        .name(dto.getName())
        .description(dto.getDescription())
        .active(dto.isActive())
        .permissions(dto.getPermissions())
        .build();
    }

    public static RoleDTO toDto(Role entity) {
      return RoleDTO.builder()
        .id(entity.getId())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())

        .name(entity.getName())
        .description(entity.getDescription())
        .active(entity.isActive())
        .permissions(entity.getPermissions())
        .build();
    }
}
