package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.common.utils.PasswordUtils;
import com.devworks.cloudcommerce.common.utils.Validator;
import com.devworks.cloudcommerce.module.account.constants.AccountStatusType;
import com.devworks.cloudcommerce.module.account.constants.RolesType;
import com.devworks.cloudcommerce.module.account.dto.UserCredentialsDTO;
import com.devworks.cloudcommerce.module.account.mapper.UserCredentialsMapper;
import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.model.User;
import com.devworks.cloudcommerce.module.account.model.UserCredentials;
import com.devworks.cloudcommerce.module.account.repository.RoleRepository;
import com.devworks.cloudcommerce.module.account.repository.UserCredentialsRepository;
import com.devworks.cloudcommerce.module.account.service.rule.UserCredentialsServiceRules;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserCredentialsService implements UserCredentialsServiceRules {
    private final RoleRepository roleRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final UserService userService;


    public UserCredentialsService(
        RoleRepository roleRepository,
        UserCredentialsRepository userCredentialsRepository,
        UserService userService
    ) {
        this.roleRepository = roleRepository;
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

        var userCredentialsWithRoles = assignRoleToUserCredentials(UserCredentialsMapper.toEntity(input, user));
        userCredentialsRepository.save(userCredentialsWithRoles);
    }

    private UserCredentials assignRoleToUserCredentials(UserCredentials credentials) {
        var roles = credentials.getRoles();

        if (roles == null) {
            roles = new HashSet<>();
            credentials.setRoles(roles);
        }

        Role customerRole = roleRepository.findByName(RolesType.CUSTOMER.getName())
            .orElseGet(() -> { // Create customer role if it doesn't exist
                Role defaultRole = new Role();
                defaultRole.setName(RolesType.CUSTOMER.getName());
                defaultRole.setDescription(RolesType.CUSTOMER.getDescription());
                return roleRepository.save(defaultRole);
            });

        roles.add(customerRole);

        return credentials;
    }

    public UserCredentials findByEmail(String email) {
        return userCredentialsRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("User with credential not found"));
    }

    public void assignCredentialsToUser(User user, UserCredentialsDTO input) {
        userService.findById(user.getId());
        userCredentialsRepository.save(UserCredentialsMapper.toEntity(input, user));
    }

    public UserCredentials findByEmailAndPassword(String email, String password) {
        var userCredentials = userCredentialsRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("Not found user with email " + email));

        if(PasswordUtils.matches(password, userCredentials.getPasswordHash())) {
            throw new BadRequestException("Invalid password");
        }

        return userCredentials;
    }
}
