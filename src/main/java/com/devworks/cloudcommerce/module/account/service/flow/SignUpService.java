package com.devworks.cloudcommerce.module.account.service.flow;

import com.devworks.cloudcommerce.common.security.EncryptingService;
import com.devworks.cloudcommerce.module.account.constants.RolesTypes;
import com.devworks.cloudcommerce.module.account.dto.UserCredentialsDto;
import com.devworks.cloudcommerce.module.account.dto.input.SignUpInput;
import com.devworks.cloudcommerce.module.account.mapper.UserMapper;
import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.model.User;
import com.devworks.cloudcommerce.module.account.service.UserCredentialsService;
import com.devworks.cloudcommerce.module.account.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
public class SignUpService {
    private final EncryptingService encryptingService;
    private final UserService userService;
    private final UserCredentialsService userCredentialsService;

    public SignUpService(
            EncryptingService encryptingService, UserService userService,
            UserCredentialsService userCredentialsService
    ) {
        this.encryptingService = encryptingService;
        this.userService = userService;
        this.userCredentialsService = userCredentialsService;
    }


    @Transactional
    public void execute(SignUpInput input) {
        var user = new User();
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());

        userService.create(UserMapper.toDto(user));

        var salt = encryptingService.generateSalt();
        var combinedPasswordAndSalt = input.getPassword() + salt;
        var passwordHash = encryptingService.encrypt(combinedPasswordAndSalt);

        var userCredentials = new UserCredentialsDto();
        userCredentials.setEmail(input.getEmail());
        userCredentials.setPasswordSalt(salt);
        userCredentials.setPasswordHash(passwordHash);

        userCredentialsService.create(userCredentials);
    }
}
