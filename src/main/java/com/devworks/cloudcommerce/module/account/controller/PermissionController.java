package com.devworks.cloudcommerce.module.account.controller;

import com.devworks.cloudcommerce.module.account.dto.input.permission.CreateInput;
import com.devworks.cloudcommerce.module.account.model.Permission;
import com.devworks.cloudcommerce.module.account.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public ResponseEntity<Permission> create(@Valid @RequestBody CreateInput input) {
        return ResponseEntity.status(HttpStatus.OK).body(permissionService.create(input));
    }
}
