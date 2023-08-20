package com.devworks.cloudcommerce.module.user.mapper;

import com.devworks.cloudcommerce.module.user.model.User;
import com.devworks.cloudcommerce.shared.util.GenericDto;

public class UserMapper {
    private UserMapper() {
        throw new IllegalStateException("You cannot instantiate a utility class");
    }

    public static User toEntity(GenericDto<User> dto) {
        return GenericDto.toEntity(dto, User.class);
    }

    public static GenericDto<User> toDto(User user) {
        return GenericDto.toDto(user);
    }
}
