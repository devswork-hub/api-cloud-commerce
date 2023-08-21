package com.devworks.cloudcommerce.module.user.service.rule;

import com.devworks.cloudcommerce.module.user.dto.UserDto;
import com.devworks.cloudcommerce.module.user.model.User;

import java.util.List;

public interface UserServiceRules {
    UserDto create(UserDto request);
    User findByEmail(String email);
    List<User> findAll();
}
