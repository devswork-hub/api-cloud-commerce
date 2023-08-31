package com.devworks.cloudcommerce.module.account.controller.flow;

import com.devworks.cloudcommerce.module.account.dto.input.LoginInput;
import com.devworks.cloudcommerce.module.account.dto.output.LoginOutput;
import com.devworks.cloudcommerce.module.account.service.flow.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<LoginOutput> login(@Valid @RequestBody LoginInput input) {
        return  ResponseEntity.status(HttpStatus.OK).body(loginService.execute(input));
    }
}
