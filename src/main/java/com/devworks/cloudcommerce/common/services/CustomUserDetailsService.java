package com.devworks.cloudcommerce.common.services;

import com.devworks.cloudcommerce.module.account.service.UserCredentialsService;
import com.devworks.cloudcommerce.module.account.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final UserCredentialsService userCredentialsService;

    public CustomUserDetailsService(UserService userService, UserCredentialsService userCredentialsService) {
        this.userService = userService;
        this.userCredentialsService = userCredentialsService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var user = userService.findByEmail(email);
        var userCredentials = userCredentialsService.findByEmail(email);

        return new User(
            userCredentials.getEmail(),
            userCredentials.getPassword(),
            true,
            true,
            true,
            true,
            user.getAuthorities());
    }
}