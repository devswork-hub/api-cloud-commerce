package com.devworks.cloudcommerce.module.account.mapper;

import com.devworks.cloudcommerce.module.account.dto.DepartmentDTO;
import com.devworks.cloudcommerce.module.account.model.Department;

public class DepartmentMapper {
    private DepartmentMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static Department toEntity(Department dto) {
        return Department.builder()
            .id(dto.getId())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())

            .name(dto.getName())
            .active(dto.isActive())
            .build();
    }

    public static DepartmentDTO toDto(Department entity) {
        return DepartmentDTO.builder()
            .id(entity.getId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())

            .name(entity.getName())
            .active(entity.isActive())
            .build();
    }
}
