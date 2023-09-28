package com.devworks.cloudcommerce.module.account.controller;

import com.devworks.cloudcommerce.module.account.dto.input.RoleResourceAssignInput;
import com.devworks.cloudcommerce.module.account.mapper.RoleMapper;
import com.devworks.cloudcommerce.module.account.model.RoleResource;
import com.devworks.cloudcommerce.module.account.service.RoleResourceService;
import com.devworks.cloudcommerce.module.account.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{role_uuid}/resources")
    public ResponseEntity<List<RoleResource>> findRoleResourcesByRole(
        @PathVariable("role_uuid") UUID uuid
    ) {
        var role = roleService.findById(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(
            roleResourceService.findRoleResourcesByRole(RoleMapper.toEntity(role))
        );
    }

    @PostMapping("/{role_uuid}/resources")
    public ResponseEntity<Void> assignResourcesOfARole(
        @PathVariable("role_uuid") UUID roleUUID,
        @RequestBody RoleResourceAssignInput input
    ) {
        roleResourceService.assignResourceToRole(roleUUID, input);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
