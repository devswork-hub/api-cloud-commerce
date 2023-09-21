package com.devworks.cloudcommerce.common.seed;

import com.devworks.cloudcommerce.module.account.constants.RolesType;
import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class RoleSeed {
    private final RoleRepository roleRepository;

    public RoleSeed(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        Set<Role> roles = new HashSet<>();

        for (RolesType rolesType : RolesType.values()) {
            if (!roleRepository.existsByName(rolesType.getName())) {
                var role = new Role();
                role.setCreatedAt(LocalDateTime.now());
                role.setName(rolesType.getName());
                role.setDescription(rolesType.getDescription());
                role.setActive(true);
                roles.add(role);
            }
        }

        roleRepository.saveAll(roles);
    }
}
