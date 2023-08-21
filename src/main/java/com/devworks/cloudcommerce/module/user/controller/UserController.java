package com.devworks.cloudcommerce.module.user.controller;

import com.devworks.cloudcommerce.module.user.dto.UserDto;
import com.devworks.cloudcommerce.module.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseEntity<UserDto> create(@RequestBody UserDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.create(request));
    }
}
