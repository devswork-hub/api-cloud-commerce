package com.devworks.cloudcommerce.common.services;

import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.module.account.repository.UserCredentialsRepository;
import com.devworks.cloudcommerce.module.account.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserCredentialsRepository userCredentialsRepository;
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserCredentialsRepository userCredentialsRepository,
                                    UserRepository userRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("No user found with email " + email));

        var userCredentials = userCredentialsRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("No user found with email " + email));

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