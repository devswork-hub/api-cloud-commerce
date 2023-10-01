package com.devworks.cloudcommerce.module.account.mapper;

import com.devworks.cloudcommerce.module.account.dto.ModuleDTO;
import com.devworks.cloudcommerce.module.account.model.Module;

public class ModuleMapper {
    private ModuleMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static Module toEntity(ModuleDTO dto) {
        return Module.builder()
            .id(dto.getId())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())

            .name(dto.getName())
            .active(dto.isActive())
            .build();
    }

    public static ModuleDTO toDto(Module entity) {
        return ModuleDTO.builder()
            .id(entity.getId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())

            .name(entity.getName())
            .active(entity.isActive())
            .build();
    }
}
