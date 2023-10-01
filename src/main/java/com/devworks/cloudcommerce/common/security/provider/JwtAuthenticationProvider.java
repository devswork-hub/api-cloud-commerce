package com.devworks.cloudcommerce.common.security.provider;

import com.devworks.cloudcommerce.common.exceptions.CustomAuthenticationException;
import com.devworks.cloudcommerce.module.account.constants.AccountStatusType;
import com.devworks.cloudcommerce.module.account.mapper.UserMapper;
import com.devworks.cloudcommerce.module.account.service.CredentialsService;
import com.devworks.cloudcommerce.module.account.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final CredentialsService credentialsService;
    private final UserService userService;

    public JwtAuthenticationProvider(CredentialsService credentialsService, UserService userService) {
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        var userDetails = credentialsService.findByEmailAndPassword(email, password);

        if (userDetails.getAccountStatus() == AccountStatusType.AWAITING_CONFIRMATION) {
            throw new CustomAuthenticationException("User account is awaiting confirmation. Please confirm your email firstly.");
        }

        var user = UserMapper.toEntity(userService.findByEmail(email));

        return new UsernamePasswordAuthenticationToken(
            email, password, user.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
