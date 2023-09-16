package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.module.account.constants.UserType;
import com.devworks.cloudcommerce.module.account.dto.UserDto;
import com.devworks.cloudcommerce.module.account.mapper.UserMapper;
import com.devworks.cloudcommerce.module.account.model.User;
import com.devworks.cloudcommerce.module.account.repository.UserRepository;
import com.devworks.cloudcommerce.module.account.service.rule.UserServiceRules;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserServiceRules {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto create(UserDto input) {
        var existsEmail = userRepository.findByEmail(input.getEmail());
        if(existsEmail.isPresent())
            throw new BadRequestException("User with email already exists!");

        var createdUser = userRepository.save(UserMapper.toEntity(input));
        return UserMapper.toDto(createdUser);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("User with email not found"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public List<User> getAllCustomers() {
        return userRepository.findByUserType(UserType.CUSTOMER);
    }
}
