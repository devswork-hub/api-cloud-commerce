package com.devworks.cloudcommerce.module.account.service.rule;

import com.devworks.cloudcommerce.module.account.model.Role;

import java.util.List;
import java.util.UUID;

public interface RoleServiceRules {
    Role create(Role request);
    List<Role> findAll();
    Role findById(UUID id);
    void delete(UUID id);
}
