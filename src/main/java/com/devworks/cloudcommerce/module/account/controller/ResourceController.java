package com.devworks.cloudcommerce.module.account.controller;

import com.devworks.cloudcommerce.module.account.dto.ResourceDTO;
import com.devworks.cloudcommerce.module.account.dto.input.UpdateResourceInput;
import com.devworks.cloudcommerce.module.account.model.Resource;
import com.devworks.cloudcommerce.module.account.service.ResourceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/resource")
@Validated
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

    @PutMapping("/{id}")
    public ResponseEntity<Resource> update(
        @Valid @PathVariable("id") UUID id,
        @Valid @RequestBody UpdateResourceInput dto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(resourceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        resourceService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
