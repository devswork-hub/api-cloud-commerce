package com.devworks.cloudcommerce.module.account.controller.flow;

import com.devworks.cloudcommerce.module.account.dto.input.RegisterInput;
import com.devworks.cloudcommerce.module.account.service.flow.Register;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final Register register;

    public RegisterController(Register register) {
        this.register = register;
    }

    @PostMapping
    public ResponseEntity<Void> signUp(@Valid @RequestBody RegisterInput input) {
        register.execute(input);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
