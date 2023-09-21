package com.devworks.cloudcommerce.common.security.filter;

import com.devworks.cloudcommerce.common.exceptions.CustomAuthenticationException;
import com.devworks.cloudcommerce.common.security.JwtService;
import com.devworks.cloudcommerce.common.utils.HttpUtils;
import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.service.RoleService;
import com.devworks.cloudcommerce.module.account.service.UserCredentialsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserCredentialsService userCredentialsService;
    private final RoleService roleService;

    public JwtTokenFilter(JwtService jwtService, UserCredentialsService userCredentialsService, RoleService roleService) {
        this.jwtService = jwtService;
        this.userCredentialsService = userCredentialsService;
        this.roleService = roleService;
    }

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        var token = HttpUtils.getHeaderToken(request);

        if (token != null)
            authenticateByToken(token, request);

        filterChain.doFilter(request, response);
    }

    private void authenticateByToken(String token, HttpServletRequest request) {
        var subject = jwtService.getSubject(token);
        var userCredentials = userCredentialsService.findByEmail(subject);
        var auth = new UsernamePasswordAuthenticationToken(subject, null, userCredentials.getAuthorities());

        var tokenRoles = jwtService.getRoles(token);

        /*
         * Check that all token roles exist on the system
         * */
        var invalidRoles = tokenRoles.stream()
                .filter(role -> !roleService.existsRole(role))
                .toList();

        if (!invalidRoles.isEmpty())
            throw new CustomAuthenticationException("Invalid roles in token: " + invalidRoles);

        /*
         * Check if the user has all the roles assigned to him
         * */
        var userRoleNames = userCredentials.getRoles().stream()
                .map(Role::getName)
                .toList();

        var missingRoles = tokenRoles.stream()
                .filter(role -> !userRoleNames.contains(role))
                .toList();

        if (!missingRoles.isEmpty())
            throw new CustomAuthenticationException("User does not have the required roles: " + missingRoles);

        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}