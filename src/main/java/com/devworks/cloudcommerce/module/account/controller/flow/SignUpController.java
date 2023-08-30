package com.devworks.cloudcommerce.module.account.controller.flow;

import com.devworks.cloudcommerce.module.account.dto.UserDto;
import com.devworks.cloudcommerce.module.account.dto.inputs.SignUpInput;
import com.devworks.cloudcommerce.module.account.service.flow.SignUpService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-up")
public class SignUpController {
    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpInput input) {
        signUpService.execute(input);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
