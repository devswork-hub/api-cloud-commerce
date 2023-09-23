package com.devworks.cloudcommerce.module.account.service.rule;

import com.devworks.cloudcommerce.module.account.dto.RoleDTO;

import java.util.List;
import java.util.UUID;

public interface RoleServiceRules {
    RoleDTO create(RoleDTO request);
    List<RoleDTO> findAll();
    RoleDTO findById(UUID id);
    void delete(UUID id);
}
