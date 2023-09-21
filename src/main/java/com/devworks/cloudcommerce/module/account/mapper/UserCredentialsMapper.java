package com.devworks.cloudcommerce.module.account.mapper;

import com.devworks.cloudcommerce.module.account.dto.UserCredentialsDTO;
import com.devworks.cloudcommerce.module.account.model.User;
import com.devworks.cloudcommerce.module.account.model.UserCredentials;

public class UserCredentialsMapper {
    private UserCredentialsMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static UserCredentials toEntity(UserCredentialsDTO dto, User user) {
        return UserCredentials.builder()
            .id(dto.getId())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())

            .email(dto.getEmail())
            .username(dto.getUsername())
            .passwordHash(dto.getPasswordHash())
            .passwordSalt(dto.getPasswordSalt())
            .accountStatus(dto.getAccountStatus())
            .roles(dto.getRoles())
            .user(user)
            .build();
    }

    public static UserCredentialsDTO toDto(UserCredentials entity) {
        return UserCredentialsDTO.builder()
            .id(entity.getId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())

            .email(entity.getEmail())
            .username(entity.getUsername())
            .passwordHash(entity.getPasswordHash())
            .passwordSalt(entity.getPasswordSalt())
            .accountStatus(entity.getAccountStatus())
            .roles(entity.getRoles())
            .userId(entity.getUser().getId())
            .build();
    }
}
