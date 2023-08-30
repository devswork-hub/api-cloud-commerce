package com.devworks.cloudcommerce.module.account.mapper;

import com.devworks.cloudcommerce.module.account.dto.UserCredentialsDto;
import com.devworks.cloudcommerce.module.account.model.UserCredentials;

public class UserCredentialsMapper {
    private UserCredentialsMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static UserCredentials toEntity(UserCredentialsDto dto) {
        return UserCredentials.builder()
            .id(dto.getId())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())

            .email(dto.getEmail())
            .username(dto.getUsername())
            .password(dto.getPassword())
            .accountStatus(dto.getAccountStatus())
            .build();
    }

    public static UserCredentialsDto toDto(UserCredentials entity) {
        return UserCredentialsDto.builder()
            .id(entity.getId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())

            .email(entity.getEmail())
            .username(entity.getUsername())
            .password(entity.getPassword())
            .accountStatus(entity.getAccountStatus())
            .build();
    }
}
