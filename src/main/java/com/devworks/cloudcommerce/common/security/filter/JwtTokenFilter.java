package com.devworks.cloudcommerce.common.security.filter;

import com.devworks.cloudcommerce.common.exceptions.CustomAuthenticationException;
import com.devworks.cloudcommerce.common.security.JwtService;
import com.devworks.cloudcommerce.common.utils.HttpUtils;
import com.devworks.cloudcommerce.module.account.mapper.UserMapper;
import com.devworks.cloudcommerce.module.account.model.Role;
import com.devworks.cloudcommerce.module.account.service.RoleService;
import com.devworks.cloudcommerce.module.account.service.UserService;
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
    private final RoleService roleService;
    private final UserService userService;

    public JwtTokenFilter(JwtService jwtService, RoleService roleService, UserService userService) {
        this.jwtService = jwtService;
        this.roleService = roleService;
        this.userService = userService;
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
        var user = userService.findByEmail(subject);
        var auth = new UsernamePasswordAuthenticationToken(
            subject,
            null,
            UserMapper
                .toEntity(user)
                .getAuthorities()
        );

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
        var userRoleNames = user.getRoles().stream()
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