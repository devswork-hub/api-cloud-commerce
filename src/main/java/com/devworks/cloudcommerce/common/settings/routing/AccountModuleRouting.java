package com.devworks.cloudcommerce.common.settings.routing;

import com.devworks.cloudcommerce.module.account.routing.routes.RoleRoute;
import com.devworks.cloudcommerce.module.account.routing.routes.UserRoute;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.stereotype.Component;

@Component
public class AccountModuleRouting extends AbstractHttpConfigurer<AccountModuleRouting, HttpSecurity> {
    public void accountRoutingChains(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/sign-up").permitAll()
            .requestMatchers(HttpMethod.POST, "/login").permitAll()

            .requestMatchers(HttpMethod.GET, UserRoute.BASE.getValue()).authenticated()
            .requestMatchers(HttpMethod.GET, UserRoute.ALL_CHILDREN.getValue()).authenticated()
            .requestMatchers(HttpMethod.POST, UserRoute.BASE.getValue()).permitAll()
            .requestMatchers(HttpMethod.DELETE, UserRoute.ALL_CHILDREN.getValue()).permitAll()

            .requestMatchers(HttpMethod.POST, RoleRoute.BASE.getValue()).permitAll()
            .requestMatchers(HttpMethod.GET, RoleRoute.BASE.getValue()).permitAll()
            .requestMatchers(HttpMethod.GET, RoleRoute.ALL_CHILDREN.getValue()).permitAll()
            .requestMatchers(HttpMethod.DELETE, RoleRoute.ALL_CHILDREN.getValue()).permitAll());
    }
}
