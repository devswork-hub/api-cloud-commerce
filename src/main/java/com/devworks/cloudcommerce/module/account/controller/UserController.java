package com.devworks.cloudcommerce.module.account.controller;

import com.devworks.cloudcommerce.module.account.dto.UserDto;
import com.devworks.cloudcommerce.module.account.dto.inputs.SignUpInput;
import com.devworks.cloudcommerce.module.account.model.User;
import com.devworks.cloudcommerce.module.account.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/check")
    public ResponseEntity<String> check() {
        return ResponseEntity.status(HttpStatus.OK).body("This is ok");
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto input) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.create(input));
    }

    @GetMapping("/email")
    public ResponseEntity<User> findByEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByEmail(email));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
