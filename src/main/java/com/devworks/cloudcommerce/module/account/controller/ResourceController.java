package com.devworks.cloudcommerce.module.account.controller;

import com.devworks.cloudcommerce.module.account.dto.ResourceDTO;
import com.devworks.cloudcommerce.module.account.model.Resource;
import com.devworks.cloudcommerce.module.account.service.ResourceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public ResponseEntity<Resource> create(@Valid @RequestBody ResourceDTO request) {
        return ResponseEntity.status(HttpStatus.OK).body(resourceService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<Resource>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(resourceService.findAll());
    }

}
