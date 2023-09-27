package com.devworks.cloudcommerce.module.account.mapper;

import com.devworks.cloudcommerce.module.account.dto.ResourceDTO;
import com.devworks.cloudcommerce.module.account.model.Resource;

public class ResourceMapper {
    private ResourceMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static Resource toEntity(ResourceDTO dto) {
        return Resource.builder()
            .id(dto.getId())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())

            .name(dto.getName())
            .path(dto.getPath())
            .active(dto.isActive())
            .actions(dto.getActions())
            .build();
    }

    public static ResourceDTO toDto(Resource entity) {
        return ResourceDTO.builder()
            .id(entity.getId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())

            .name(entity.getName())
            .path(entity.getPath())
            .active(entity.isActive())
            .actions(entity.getActions())
            .build();
    }
}
