package com.devworks.cloudcommerce.common.security.filter;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.security.JwtService;
import com.devworks.cloudcommerce.common.utils.HttpUtils;
import com.devworks.cloudcommerce.module.account.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;

    public JwtFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        var token = HttpUtils.getHeaderToken(request);

        if(token != null)
            authenticateByToken(token);

        filterChain.doFilter(request, response);
    }

    private void authenticateByToken(String token) {
        try {
            var subject = jwtService.getSubject(token);
            var user = userService.findByEmail(subject);

            SecurityContextHolder
                .getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities())
                );
        } catch(Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
