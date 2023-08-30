package com.devworks.cloudcommerce.module.account.service.flow;

import com.devworks.cloudcommerce.module.account.dto.inputs.SignUpInput;
import com.devworks.cloudcommerce.module.account.mapper.UserCredentialsMapper;
import com.devworks.cloudcommerce.module.account.mapper.UserMapper;
import com.devworks.cloudcommerce.module.account.model.User;
import com.devworks.cloudcommerce.module.account.model.UserCredentials;
import com.devworks.cloudcommerce.module.account.service.UserCredentialsService;
import com.devworks.cloudcommerce.module.account.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignUpService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserCredentialsService userCredentialsService;

    public SignUpService(
        PasswordEncoder passwordEncoder,
        UserService userService,
        UserCredentialsService userCredentialsService
    ) {
        this.passwordEncoder = passwordEncoder;
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

        var userCredentials = new UserCredentials();
        userCredentials.setEmail(input.getEmail());
        userCredentials.setPassword(passwordEncoder.encode(input.getPassword()));
        userCredentialsService.assignCredentialsToUser(user, UserCredentialsMapper.toDto(userCredentials));
    }
}
