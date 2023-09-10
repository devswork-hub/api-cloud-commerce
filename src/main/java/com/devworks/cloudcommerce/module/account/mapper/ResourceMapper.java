package com.devworks.cloudcommerce.module.account.mapper;

import com.devworks.cloudcommerce.module.account.dto.ResourceDto;
import com.devworks.cloudcommerce.module.account.model.Resource;

public class ResourceMapper {
    private ResourceMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static Resource toEntity(Resource dto) {
        return Resource.builder()
            .id(dto.getId())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())

            .name(dto.getName())
            .moduleId(dto.getModuleId())
            .link(dto.getLink())
            .resourcePermissions(dto.getResourcePermissions())
            .build();
    }

    public static ResourceDto toDto(Resource entity) {
        return ResourceDto.builder()
            .id(entity.getId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())

            .name(entity.getName())
            .moduleId(entity.getModuleId().getId())
            .link(entity.getLink())
            .resourcePermissions(entity.getResourcePermissions())
            .build();
    }
}
