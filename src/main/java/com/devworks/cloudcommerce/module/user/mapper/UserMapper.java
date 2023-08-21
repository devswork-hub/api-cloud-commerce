package com.devworks.cloudcommerce.module.user.mapper;

import com.devworks.cloudcommerce.module.user.dto.UserDto;
import com.devworks.cloudcommerce.module.user.model.User;

public class UserMapper {
    private UserMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static User toEntity(UserDto dto) {
        return User.builder()
            .id(dto.getId())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())

            .firstName(dto.getFirstName())
            .lastName(dto.getLastName())
            .email(dto.getEmail())
            .cpf(dto.getCpf())
            .phoneNumber(dto.getPhoneNumber())
            .phoneCodeArea(dto.getPhoneCountryCode())
            .phoneCodeArea(dto.getPhoneCodeArea())
            .build();
    }

    public static UserDto toDto(User entity) {
        return UserDto.builder()
            .id(entity.getId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())

            .firstName(entity.getFirstName())
            .lastName(entity.getLastName())
            .email(entity.getEmail())
            .cpf(entity.getCpf())
            .phoneNumber(entity.getPhoneNumber())
            .phoneCodeArea(entity.getPhoneCountryCode())
            .phoneCodeArea(entity.getPhoneCodeArea())
            .build();
    }
}
