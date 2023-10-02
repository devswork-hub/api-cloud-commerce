package com.devworks.cloudcommerce.module.account.controller;

import com.devworks.cloudcommerce.module.account.service.AccessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/access")
public class AccessController {
    private final AccessService accessService;

    public AccessController(AccessService accessService) {
        this.accessService = accessService;
    }

    @GetMapping
    public ResponseEntity<?> execute(@RequestBody AccessService.AccessInput input) {
        return ResponseEntity.status(HttpStatus.OK).body(accessService.execute(input));
    }
}
