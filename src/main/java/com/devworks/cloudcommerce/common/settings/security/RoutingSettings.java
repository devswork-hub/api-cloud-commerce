package com.devworks.cloudcommerce.common.settings.security;

import com.devworks.cloudcommerce.common.security.filter.JwtTokenFilter;
import com.devworks.cloudcommerce.common.security.provider.JwtAuthenticationProvider;
import com.devworks.cloudcommerce.common.settings.routing.AccountModuleRouting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class RoutingSettings {
    private final AccountModuleRouting accountModuleRouting;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final JwtTokenFilter jwtTokenFilter;

    public RoutingSettings(AccountModuleRouting accountModuleRouting, JwtAuthenticationProvider jwtAuthenticationProvider, JwtTokenFilter jwtTokenFilter) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.jwtTokenFilter = jwtTokenFilter;
        this.accountModuleRouting = accountModuleRouting;
    }

    @Bean
    public SecurityFilterChain routingChain(HttpSecurity http) throws Exception {
        http.apply(accountModuleRouting).accountRoutingChains(http);
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
            .authenticationProvider(jwtAuthenticationProvider)
            .logout(l ->
                l.logoutSuccessHandler(
                    (request, response, authentication) -> SecurityContextHolder.clearContext()
                )
            );

        return http.build();
    }
}
