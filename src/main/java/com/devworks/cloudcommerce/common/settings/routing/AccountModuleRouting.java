package com.devworks.cloudcommerce.common.settings.routing;

import com.devworks.cloudcommerce.module.account.routing.routes.RoleRoute;
import com.devworks.cloudcommerce.module.account.routing.routes.UserRoute;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class AccountModuleRouting extends AbstractHttpConfigurer<AccountModuleRouting, HttpSecurity> {
    public void accountRoutingChains(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, UserRoute.DEFAULT.getValue()).permitAll()
            .requestMatchers(HttpMethod.GET, UserRoute.DEFAULT.getValue()).permitAll()
            .requestMatchers(HttpMethod.GET, UserRoute.USER_ALL_ROUTES_CHILD.getValue()).permitAll()
            .requestMatchers(HttpMethod.DELETE, UserRoute.USER_ALL_ROUTES_CHILD.getValue()).permitAll()

            .requestMatchers(HttpMethod.POST, RoleRoute.DEFAULT.getValue()).permitAll()
            .requestMatchers(HttpMethod.GET, RoleRoute.DEFAULT.getValue()).permitAll()
            .requestMatchers(HttpMethod.GET, RoleRoute.ALL_ROUTES_CHILD.getValue()).permitAll()
            .requestMatchers(HttpMethod.DELETE, RoleRoute.ALL_ROUTES_CHILD.getValue()).permitAll()
            .requestMatchers(HttpMethod.POST, "/sign-up/").permitAll()
            .requestMatchers(HttpMethod.POST, "/login").permitAll());
    }
}
