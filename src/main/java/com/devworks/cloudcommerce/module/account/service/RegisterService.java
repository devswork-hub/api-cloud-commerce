package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.security.EncryptingService;
import com.devworks.cloudcommerce.common.utils.PasswordUtils;
import com.devworks.cloudcommerce.module.account.constants.AccountStatusType;
import com.devworks.cloudcommerce.module.account.constants.RolesType;
import com.devworks.cloudcommerce.module.account.dto.UserCredentialsDTO;
import com.devworks.cloudcommerce.module.account.dto.input.RegisterInput;
import com.devworks.cloudcommerce.module.account.mapper.UserMapper;
import com.devworks.cloudcommerce.module.account.model.*;
import com.devworks.cloudcommerce.module.account.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
public class RegisterService {
    private final EncryptingService encryptingService;
    private final UserService userService;
    private final CredentialsService credentialsService;
    private final RoleRepository roleRepository;

    public RegisterService(
        EncryptingService encryptingService, UserService userService,
        CredentialsService credentialsService,
        RoleRepository roleRepository
    ) {
        this.encryptingService = encryptingService;
        this.userService = userService;
        this.credentialsService = credentialsService;
        this.roleRepository = roleRepository;
    }


    @Transactional
    public void execute(RegisterInput input) {
        var user = new User();
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());

        var userWithAssignerRoles = assignDefaultRolesToUser(user);
        var createdUser = userService.create(UserMapper.toDto(userWithAssignerRoles));

        var salt = encryptingService.generateSalt();
        var combinedPasswordAndSalt = input.getPassword() + salt;
        var passwordHash = PasswordUtils.encode(combinedPasswordAndSalt);

        var userCredentials = new UserCredentialsDTO();
        userCredentials.setEmail(input.getEmail());
        userCredentials.setPasswordSalt(salt);
        userCredentials.setPasswordHash(passwordHash);
        userCredentials.setUserId(createdUser.getId());
        userCredentials.setAccountStatus(AccountStatusType.ACTIVE);

        credentialsService.create(userCredentials);
    }

    private User assignDefaultRolesToUser(User input) {
        var roles = input.getRoles();

        if(roles == null) {
            roles = new HashSet<>();
            input.setRoles(roles);
        }

        var customerRole = roleRepository.findByName(RolesType.CUSTOMER.getName())
            .orElseGet(() -> {
                var defaultCustomerRole = new Role();
               defaultCustomerRole.setName(RolesType.CUSTOMER.getName());
               defaultCustomerRole.setDescription(RolesType.CUSTOMER.getDescription());
               return roleRepository.save(defaultCustomerRole);
            });

        roles.add(customerRole);

        return input;
    }
}
