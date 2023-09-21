package com.devworks.cloudcommerce.common.security.provider;

import com.devworks.cloudcommerce.common.exceptions.CustomAuthenticationException;
import com.devworks.cloudcommerce.module.account.constants.AccountStatusType;
import com.devworks.cloudcommerce.module.account.service.UserCredentialsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserCredentialsService userCredentialsService;

    public JwtAuthenticationProvider(UserCredentialsService userCredentialsService) {
        this.userCredentialsService = userCredentialsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        var userDetails = userCredentialsService.findByEmail(email);

        if(userDetails.getAccountStatus().equals(AccountStatusType.AWAITING_CONFIRMATION))
            throw new CustomAuthenticationException("Please, confirm your email firstly");

        return new UsernamePasswordAuthenticationToken(
            email, password, userDetails.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
