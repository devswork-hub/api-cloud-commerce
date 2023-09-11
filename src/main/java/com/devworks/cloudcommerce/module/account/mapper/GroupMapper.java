package com.devworks.cloudcommerce.module.account.mapper;

import com.devworks.cloudcommerce.module.account.dto.GroupDto;
import com.devworks.cloudcommerce.module.account.model.Group;

public class GroupMapper {
    private GroupMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static Group toEntity(Group dto) {
        return Group.builder()
            .id(dto.getId())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())

            .name(dto.getName())
            .priority(dto.getPriority())
            .resources(dto.getResources())
            .build();
    }

    public static GroupDto toDto(Group entity) {
        return GroupDto.builder()
            .id(entity.getId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())

            .name(entity.getName())
            .priority(entity.getPriority())
            .resources(entity.getResources())
            .build();
    }
}
