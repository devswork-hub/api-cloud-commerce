package com.devworks.cloudcommerce.module.account.mapper;

import com.devworks.cloudcommerce.module.account.dto.ModuleDto;
import com.devworks.cloudcommerce.module.account.model.Module;

public class ModuleMapper {
    private ModuleMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static Module toEntity(Module dto) {
        return Module.builder()
            .id(dto.getId())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())

            .name(dto.getName())
            .priority(dto.getPriority())
            .resources(dto.getResources())
            .build();
    }

    public static ModuleDto toDto(Module entity) {
        return ModuleDto.builder()
            .id(entity.getId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())

            .name(entity.getName())
            .priority(entity.getPriority())
            .resources(entity.getResources())
            .build();
    }
}
