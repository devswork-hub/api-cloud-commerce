package com.devworks.cloudcommerce.module.user.controller;

import com.devworks.cloudcommerce.module.user.model.Role;
import com.devworks.cloudcommerce.module.user.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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

    @GetMapping
    public ResponseEntity<List<Role>> findALl() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.findById(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        roleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
