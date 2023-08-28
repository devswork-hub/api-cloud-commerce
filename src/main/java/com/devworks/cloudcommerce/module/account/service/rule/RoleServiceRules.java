package com.devworks.cloudcommerce.module.account.service.rule;

import com.devworks.cloudcommerce.module.account.model.Role;

import java.util.List;

public interface RoleServiceRules {
    Role create(Role request);
    List<Role> findAll();
    Role findById(Long id);
    void delete(Long id);
}
