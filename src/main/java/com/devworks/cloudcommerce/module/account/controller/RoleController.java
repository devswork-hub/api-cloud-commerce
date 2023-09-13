package com.devworks.cloudcommerce.module.account.controller;

import com.devworks.cloudcommerce.module.account.dto.input.AssignResourcesToRoleInput;
import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> create(@Valid @RequestBody Role request) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.create(request));
    }

    @PostMapping("/assign")
    public ResponseEntity<Void> assignResourcesToRole(
        @Valid @RequestParam UUID roleId,
        @Valid @RequestBody AssignResourcesToRoleInput input
    ) {
//        roleService.assignResourcesToRole(roleId, input);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping
    public ResponseEntity<List<Role>> findALl() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        roleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
