package com.devworks.cloudcommerce.module.user.service.rule;

import com.devworks.cloudcommerce.module.user.model.Role;

import java.util.List;

public interface RoleServiceRules {
    Role create(Role request);
    List<Role> findAll();
    Role findById(Long id);
    void delete(Long id);
}
