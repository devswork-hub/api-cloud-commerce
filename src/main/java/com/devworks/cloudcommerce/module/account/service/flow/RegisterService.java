package com.devworks.cloudcommerce.module.account.service.flow;

import com.devworks.cloudcommerce.common.security.EncryptingService;
import com.devworks.cloudcommerce.common.utils.PasswordUtils;
import com.devworks.cloudcommerce.module.account.constants.AccountStatusType;
import com.devworks.cloudcommerce.module.account.dto.DepartmentDTO;
import com.devworks.cloudcommerce.module.account.dto.UserCredentialsDTO;
import com.devworks.cloudcommerce.module.account.dto.input.RegisterInput;
import com.devworks.cloudcommerce.module.account.mapper.UserMapper;
import com.devworks.cloudcommerce.module.account.model.Department;
import com.devworks.cloudcommerce.module.account.model.Resource;
import com.devworks.cloudcommerce.module.account.model.User;
import com.devworks.cloudcommerce.module.account.service.UserCredentialsService;
import com.devworks.cloudcommerce.module.account.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

        var departmentDashboard = new Department();
        var dashboardResources = new HashSet<Resource>();

        dashboardResources.add(new Resource());

        departmentDashboard.setName("Dashboard");
        departmentDashboard.setActive(true);
        departmentDashboard.setResources(dashboardResources);

    }
}
