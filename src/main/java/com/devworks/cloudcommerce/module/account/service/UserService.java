package com.devworks.cloudcommerce.module.account.service;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.module.account.constants.RolesTypes;
import com.devworks.cloudcommerce.module.account.dto.UserDto;
import com.devworks.cloudcommerce.module.account.mapper.UserMapper;
import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.model.User;
import com.devworks.cloudcommerce.module.account.repository.RoleRepository;
import com.devworks.cloudcommerce.module.account.repository.UserRepository;
import com.devworks.cloudcommerce.module.account.service.rule.UserServiceRules;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserServiceRules {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(
        UserRepository userRepository,
        RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDto create(UserDto input) {
        var existsEmail = userRepository.findByEmail(input.getEmail());
        if(existsEmail.isPresent())
            throw new BadRequestException("User with email already exists!");

        var user = assignDefaultRoleToUser(UserMapper.toEntity(input));

        var createdUser = userRepository.save(user);
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

    private User assignDefaultRoleToUser(User user) {
        var roles = user.getRoles();

        if (roles == null) {
            roles = new HashSet<>();
            user.setRoles(roles);
        }

        Role customerRole = roleRepository.findByName(RolesTypes.CUSTOMER.getName())
            .orElseGet(() -> { // Create customer role if it doesn't exist
                Role defaultRole = new Role();
                defaultRole.setName(RolesTypes.CUSTOMER.getName());
                defaultRole.setDescription(RolesTypes.CUSTOMER.getDescription());
                return roleRepository.save(defaultRole);
            });

        roles.add(customerRole);

        return user;
    }
}
