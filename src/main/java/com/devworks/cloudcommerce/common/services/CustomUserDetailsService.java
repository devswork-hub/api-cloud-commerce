package com.devworks.cloudcommerce.common.services;

import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.module.account.repository.UserCredentialsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final UserCredentialsRepository userCredentialsRepository;

    public CustomUserDetailsService(UserCredentialsRepository userCredentialsRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var userCredentials = userCredentialsRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("No user found with email " + email));

        return new User(
            userCredentials.getEmail(),
            userCredentials.getPassword(),
            true,
            true,
            true,
            true,
            userCredentials.getAuthorities());
    }
}