package com.devworks.cloudcommerce.module.account.mapper;

import com.devworks.cloudcommerce.module.account.dto.ActionDTO;
import com.devworks.cloudcommerce.module.account.model.Action;

public class ActionMapper {
    private ActionMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static Action toEntity(ActionDTO dto) {
        return Action.builder()
            .id(dto.getId())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())

            .name(dto.getName())
            .active(dto.isActive())
            .build();
    }

    public static ActionDTO toDto(Action entity) {
        return ActionDTO.builder()
            .id(entity.getId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())

            .name(entity.getName())
            .active(entity.isActive())
            .build();
    }
}
