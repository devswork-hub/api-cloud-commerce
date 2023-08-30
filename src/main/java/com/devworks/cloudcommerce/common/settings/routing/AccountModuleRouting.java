package com.devworks.cloudcommerce.common.settings.routing;

import com.devworks.cloudcommerce.module.account.routing.routes.RoleRoute;
import com.devworks.cloudcommerce.module.account.routing.routes.UserRoute;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AccountModuleRouting {
    @Bean
    public SecurityFilterChain chains(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/sign-up").permitAll()
            .requestMatchers(HttpMethod.POST, "/sign-up/**").permitAll()

            .requestMatchers(HttpMethod.POST, UserRoute.DEFAULT.getValue()).permitAll()
            .requestMatchers(HttpMethod.GET, UserRoute.DEFAULT.getValue()).permitAll()
            .requestMatchers(HttpMethod.GET, UserRoute.USER_ALL_ROUTES_CHILD.getValue()).permitAll()
            .requestMatchers(HttpMethod.DELETE, UserRoute.USER_ALL_ROUTES_CHILD.getValue()).permitAll()

            .requestMatchers(HttpMethod.POST, RoleRoute.DEFAULT.getValue()).permitAll()
            .requestMatchers(HttpMethod.GET, RoleRoute.DEFAULT.getValue()).permitAll()
            .requestMatchers(HttpMethod.GET, RoleRoute.ALL_ROUTES_CHILD.getValue()).permitAll()
            .requestMatchers(HttpMethod.DELETE, RoleRoute.ALL_ROUTES_CHILD.getValue()).permitAll());


        return http.build();
    }
}
