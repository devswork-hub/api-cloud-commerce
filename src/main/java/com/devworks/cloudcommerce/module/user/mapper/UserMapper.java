package com.devworks.cloudcommerce.module.user.mapper;

import com.devworks.cloudcommerce.module.user.model.User;
import com.devworks.cloudcommerce.shared.util.GenericDto;

public class UserMapper {
    private UserMapper() {
        throw new IllegalStateException("Você não pode instanciar essa classe de utilitário");
    }

    public static User toEntity(GenericDto<User> dto) {
        return dto.toEntity(User::new);
    }

    public static GenericDto<User> toDto(User user) {
        return GenericDto.toDto(user);
    }
}
