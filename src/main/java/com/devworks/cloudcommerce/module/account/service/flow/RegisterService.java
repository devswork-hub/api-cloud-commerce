package com.devworks.cloudcommerce.module.account.service.flow;

import com.devworks.cloudcommerce.common.security.EncryptingService;
import com.devworks.cloudcommerce.common.utils.PasswordUtils;
import com.devworks.cloudcommerce.module.account.constants.AccountStatusType;
import com.devworks.cloudcommerce.module.account.dto.UserCredentialsDTO;
import com.devworks.cloudcommerce.module.account.dto.input.RegisterInput;
import com.devworks.cloudcommerce.module.account.mapper.UserMapper;
import com.devworks.cloudcommerce.module.account.model.User;
import com.devworks.cloudcommerce.module.account.service.UserCredentialsService;
import com.devworks.cloudcommerce.module.account.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {
    private final EncryptingService encryptingService;
    private final UserService userService;
    private final UserCredentialsService userCredentialsService;

    public RegisterService(
            EncryptingService encryptingService, UserService userService,
            UserCredentialsService userCredentialsService
    ) {
        this.encryptingService = encryptingService;
        this.userService = userService;
        this.userCredentialsService = userCredentialsService;
    }


    @Transactional
    public void execute(RegisterInput input) {
        var user = new User();
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());

        var createdUser = userService.create(UserMapper.toDto(user));

        var salt = encryptingService.generateSalt();
        var combinedPasswordAndSalt = input.getPassword() + salt;
        var passwordHash = PasswordUtils.encode(combinedPasswordAndSalt);

        var userCredentials = new UserCredentialsDTO();
        userCredentials.setEmail(input.getEmail());
        userCredentials.setPasswordSalt(salt);
        userCredentials.setPasswordHash(passwordHash);
        userCredentials.setUserId(createdUser.getId());
        userCredentials.setAccountStatus(AccountStatusType.ACTIVE);

        userCredentialsService.create(userCredentials);
    }
}
