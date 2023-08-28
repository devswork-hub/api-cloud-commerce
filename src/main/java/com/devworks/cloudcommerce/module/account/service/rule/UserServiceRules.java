package com.devworks.cloudcommerce.module.account.service.rule;

import com.devworks.cloudcommerce.module.account.dto.UserDto;
import com.devworks.cloudcommerce.module.account.model.User;

import java.util.List;
import java.util.UUID;

public interface UserServiceRules {
    UserDto create(UserDto request);
    User findByEmail(String email);
    List<User> findAll();
    User findById(UUID id);
    void delete(UUID id);
}
