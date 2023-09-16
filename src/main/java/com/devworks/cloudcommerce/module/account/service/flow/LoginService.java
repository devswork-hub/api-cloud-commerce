package com.devworks.cloudcommerce.module.account.service.flow;

import com.devworks.cloudcommerce.common.security.JwtService;
import com.devworks.cloudcommerce.module.account.dto.input.LoginInput;
import com.devworks.cloudcommerce.module.account.dto.output.LoginOutput;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginService(
        AuthenticationManager authenticationManager,
        JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public LoginOutput execute(LoginInput input) {
        var auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.email(),
                input.password()
            )
        );

        var token = jwtService.generateToken(auth);
        var expiresAt = jwtService.getExpirationDate(token);

        var output = new LoginOutput();
        output.setAccessToken(token);
        output.setExpiresAt(expiresAt);

        return output;
    }
}