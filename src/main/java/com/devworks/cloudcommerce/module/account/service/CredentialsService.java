package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.common.utils.PasswordUtils;
import com.devworks.cloudcommerce.common.utils.Validator;
import com.devworks.cloudcommerce.module.account.constants.AccountStatusType;
import com.devworks.cloudcommerce.module.account.dto.CredentialsDTO;
import com.devworks.cloudcommerce.module.account.mapper.CredentialsMapper;
import com.devworks.cloudcommerce.module.account.mapper.UserMapper;
import com.devworks.cloudcommerce.module.account.model.User;
import com.devworks.cloudcommerce.module.account.model.Credentials;
import com.devworks.cloudcommerce.module.account.repository.CredentialsRepository;
import com.devworks.cloudcommerce.module.account.service.rule.UserCredentialsServiceRules;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService implements UserCredentialsServiceRules {
    private final CredentialsRepository credentialsRepository;
    private final UserService userService;

    public CredentialsService(
        CredentialsRepository credentialsRepository,
        UserService userService
    ) {
        this.credentialsRepository = credentialsRepository;
        this.userService = userService;
    }

    public void create(CredentialsDTO input) {
        if(!Validator.isValidEnum(AccountStatusType.class, input.getAccountStatus().getName()))
            throw new BadRequestException("Invalid account status");

        var existsUserCredentials = credentialsRepository.findByEmail(input.getEmail());

        if (existsUserCredentials.isPresent())
            throw new BadRequestException("Credentials with email already exists!");

        var user = userService.findById(input.getUserId());
        credentialsRepository.save(CredentialsMapper.toEntity(input, UserMapper.toEntity(user)));
    }

    public Credentials findByEmail(String email) {
        return credentialsRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("User with credential not found"));
    }

    public void assignCredentialsToUser(User user, CredentialsDTO input) {
        userService.findById(user.getId());
        credentialsRepository.save(CredentialsMapper.toEntity(input, user));
    }

    public Credentials findByEmailAndPassword(String email, String password) {
        var userCredentials = credentialsRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("Not found user with email " + email));

        if(PasswordUtils.matches(password, userCredentials.getPasswordHash())) {
            throw new BadRequestException("Invalid password");
        }

        return userCredentials;
    }
}
