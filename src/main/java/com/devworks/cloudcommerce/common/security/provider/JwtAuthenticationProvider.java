package com.devworks.cloudcommerce.common.security.provider;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.module.account.repository.UserCredentialsRepository;
import com.devworks.cloudcommerce.module.account.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserCredentialsRepository userCredentialsRepository;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationProvider(UserDetailsService userDetailsService,
                                     UserCredentialsRepository userCredentialsRepository,
                                     UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userCredentialsRepository = userCredentialsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        System.out.println("003");
        //Se eu fizer dessa forma, eu to acessando as informacoes em memorira,
        var userDetails = userDetailsService.loadUserByUsername(email);
//            var userDetails = userRepository.findByEmail(email)
//                    .orElseThrow(() -> new NotFoundException("Nao xiste"));
        System.out.println(userDetails.getUsername());

        if(userDetails != null) {
            return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
        }

        throw new BadRequestException("Authentication Error");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
