package com.devworks.cloudcommerce.module.account.routing;

import com.devworks.cloudcommerce.module.account.constants.RolesType;
import com.devworks.cloudcommerce.module.account.routing.routes.ResourceRoute;
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
            .requestMatchers(HttpMethod.POST, "/register").permitAll()
            .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()

            .requestMatchers(HttpMethod.GET, UserRoute.BASE.getValue())
                .hasAnyAuthority(
                    RolesType.ADMIN.getName(),
                    RolesType.CUSTOMER.getName()
                )
            .requestMatchers(HttpMethod.GET, UserRoute.ALL_CHILDREN.getValue()).authenticated()
            .requestMatchers(HttpMethod.POST, UserRoute.BASE.getValue()).permitAll()
            .requestMatchers(HttpMethod.DELETE, UserRoute.ALL_CHILDREN.getValue()).permitAll()

            .requestMatchers(HttpMethod.POST, RoleRoute.BASE.getValue()).permitAll()
            .requestMatchers(HttpMethod.POST, RoleRoute.ALL_CHILDREN.getValue()).permitAll()
            .requestMatchers(HttpMethod.GET, RoleRoute.BASE.getValue()).permitAll()

            // retrieve single role by id
            .requestMatchers(HttpMethod.GET, RoleRoute.ALL_CHILDREN.getValue())
                .hasAnyAuthority(
                    RolesType.MANAGER.getName(),
                    RolesType.ADMIN.getName()
                )
            .requestMatchers(HttpMethod.DELETE, RoleRoute.ALL_CHILDREN.getValue()).permitAll()

            .requestMatchers(HttpMethod.POST, ResourceRoute.BASE.getValue()).permitAll()
            .requestMatchers(HttpMethod.GET, ResourceRoute.BASE.getValue()).permitAll()
            .requestMatchers(HttpMethod.PUT, ResourceRoute.ALL_CHILDREN.getValue()).permitAll()
            .requestMatchers(HttpMethod.DELETE, ResourceRoute.ALL_CHILDREN.getValue()).permitAll()
        );
    }
}
