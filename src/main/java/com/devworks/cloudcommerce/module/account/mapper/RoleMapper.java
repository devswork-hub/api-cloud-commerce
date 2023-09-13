package com.devworks.cloudcommerce.module.account.mapper;

import com.devworks.cloudcommerce.module.account.dto.RoleDto;
import com.devworks.cloudcommerce.module.account.model.Role;

public class RoleMapper {
    private RoleMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static Role toEntity(RoleDto dto) {
      return Role.builder()
        .id(dto.getId())
        .createdAt(dto.getCreatedAt())
        .updatedAt(dto.getUpdatedAt())

        .name(dto.getName())
        .description(dto.getDescription())
        .active(dto.isActive())
        .build();
    }

    public static RoleDto toDto(Role entity) {
      return RoleDto.builder()
        .id(entity.getId())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())

        .name(entity.getName())
        .description(entity.getDescription())
        .active(entity.isActive())
        .build();
    }
}
