package com.devworks.cloudcommerce.module.user.service;

import com.devworks.cloudcommerce.module.user.mapper.UserMapper;
import com.devworks.cloudcommerce.module.user.model.User;
import com.devworks.cloudcommerce.module.user.repository.UserRepository;
import com.devworks.cloudcommerce.module.user.service.rule.UserServiceRules;
import com.devworks.cloudcommerce.shared.util.GenericDto;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserServiceRules {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GenericDto<User> create(GenericDto<User> request) {
        var existsEmail = findByEmail(request.getProperty("email"));

        if(Objects.nonNull(existsEmail)) {
            throw new RuntimeException("User with email already exists!");
        }
        System.out.println(UserMapper.toEntity(request));
        var user = userRepository.save(UserMapper.toEntity(request));
        return UserMapper.toDto(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User with email not found"));
    }
}
