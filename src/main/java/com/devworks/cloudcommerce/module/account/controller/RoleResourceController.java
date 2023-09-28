package com.devworks.cloudcommerce.module.account.controller;

import com.devworks.cloudcommerce.module.account.mapper.RoleMapper;
import com.devworks.cloudcommerce.module.account.model.RoleResource;
import com.devworks.cloudcommerce.module.account.service.RoleResourceService;
import com.devworks.cloudcommerce.module.account.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/role")
public class RoleResourceController {
    private final RoleResourceService roleResourceService;
    private final RoleService roleService;

    public RoleResourceController(RoleResourceService roleResourceService, RoleService roleService) {
        this.roleResourceService = roleResourceService;
        this.roleService = roleService;
    }

    @GetMapping("/{uuid}/resources")
    public ResponseEntity<List<RoleResource>> findRoleResourcesByRole(@PathVariable("uuid") UUID uuid) {
        var role = roleService.findById(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(
            roleResourceService.findRoleResourcesByRole(RoleMapper.toEntity(role)));
    }
}
