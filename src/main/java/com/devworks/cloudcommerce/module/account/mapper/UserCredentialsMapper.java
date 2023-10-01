package com.devworks.cloudcommerce.module.account.mapper;

import com.devworks.cloudcommerce.module.account.dto.UserCredentialsDTO;
import com.devworks.cloudcommerce.module.account.model.User;
import com.devworks.cloudcommerce.module.account.model.Credentials;

public class UserCredentialsMapper {
    private UserCredentialsMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static Credentials toEntity(UserCredentialsDTO dto, User user) {
        return Credentials.builder()
            .id(dto.getId())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())

            .email(dto.getEmail())
            .username(dto.getUsername())
            .passwordHash(dto.getPasswordHash())
            .passwordSalt(dto.getPasswordSalt())
            .accountStatus(dto.getAccountStatus())
            .user(user)
            .build();
    }

    public static UserCredentialsDTO toDto(Credentials entity) {
        return UserCredentialsDTO.builder()
            .id(entity.getId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())

            .email(entity.getEmail())
            .username(entity.getUsername())
            .passwordHash(entity.getPasswordHash())
            .passwordSalt(entity.getPasswordSalt())
            .accountStatus(entity.getAccountStatus())
            .userId(entity.getUser().getId())
            .build();
    }
}
