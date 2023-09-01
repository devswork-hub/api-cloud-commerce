package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.common.utils.PasswordUtils;
import com.devworks.cloudcommerce.module.account.dto.UserCredentialsDto;
import com.devworks.cloudcommerce.module.account.mapper.UserCredentialsMapper;
import com.devworks.cloudcommerce.module.account.model.User;
import com.devworks.cloudcommerce.module.account.model.UserCredentials;
import com.devworks.cloudcommerce.module.account.repository.UserCredentialsRepository;
import com.devworks.cloudcommerce.module.account.service.rule.UserCredentialsServiceRules;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsService implements UserCredentialsServiceRules {
    private final UserCredentialsRepository userCredentialsRepository;
    private final UserService userService;

    public UserCredentialsService(
        UserCredentialsRepository userCredentialsRepository,
        UserService userService
    ) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.userService = userService;
    }

    public void create(UserCredentialsDto input) {
        var existsEmail = userCredentialsRepository.findByEmail(input.getEmail());

        if(existsEmail.isPresent())
            throw new BadRequestException("User credentials is already exists");

        userCredentialsRepository.save(UserCredentialsMapper.toEntity(input));
    }

    public UserCredentials findByEmail(String email) {
        return userCredentialsRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("User with credential not found"));
    }

    public void assignCredentialsToUser(User user, UserCredentialsDto input) {
        userService.findById(user.getId());
        userCredentialsRepository.save(UserCredentialsMapper.toEntity(input));
    }

    public UserCredentials findByEmailAndPassword(String email, String password) {
        var userCredentials = userCredentialsRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("Not found user with email " + email));

        if(PasswordUtils.matches(password, userCredentials.getPassword())) {
            return userCredentials;
        }

        throw new BadRequestException("Invalid email/username and password");
    }
}
