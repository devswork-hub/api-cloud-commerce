package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.common.utils.PasswordUtils;
import com.devworks.cloudcommerce.common.utils.Validator;
import com.devworks.cloudcommerce.module.account.constants.AccountStatusType;
import com.devworks.cloudcommerce.module.account.dto.UserCredentialsDTO;
import com.devworks.cloudcommerce.module.account.mapper.UserCredentialsMapper;
import com.devworks.cloudcommerce.module.account.mapper.UserMapper;
import com.devworks.cloudcommerce.module.account.model.User;
import com.devworks.cloudcommerce.module.account.model.Credentials;
import com.devworks.cloudcommerce.module.account.repository.UserCredentialsRepository;
import com.devworks.cloudcommerce.module.account.service.rule.UserCredentialsServiceRules;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService implements UserCredentialsServiceRules {
    private final UserCredentialsRepository userCredentialsRepository;
    private final UserService userService;

    public CredentialsService(
        UserCredentialsRepository userCredentialsRepository,
        UserService userService
    ) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.userService = userService;
    }

    public void create(UserCredentialsDTO input) {
        if(!Validator.isValidEnum(AccountStatusType.class, input.getAccountStatus().getName()))
            throw new BadRequestException("Invalid account status");

        var existsUserCredentials = userCredentialsRepository.findByEmail(input.getEmail());

        if (existsUserCredentials.isPresent())
            throw new BadRequestException("Credentials with email already exists!");

        var user = userService.findById(input.getUserId());
        userCredentialsRepository.save(UserCredentialsMapper.toEntity(input, UserMapper.toEntity(user)));
    }

    public Credentials findByEmail(String email) {
        return userCredentialsRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("User with credential not found"));
    }

    public void assignCredentialsToUser(User user, UserCredentialsDTO input) {
        userService.findById(user.getId());
        userCredentialsRepository.save(UserCredentialsMapper.toEntity(input, user));
    }

    public Credentials findByEmailAndPassword(String email, String password) {
        var userCredentials = userCredentialsRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("Not found user with email " + email));

        if(PasswordUtils.matches(password, userCredentials.getPasswordHash())) {
            throw new BadRequestException("Invalid password");
        }

        return userCredentials;
    }
}
