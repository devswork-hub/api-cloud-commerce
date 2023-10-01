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

            /*
            * User definitions
            * */
            .requestMatchers(HttpMethod.GET, UserRoute.BASE.getValue())
                .hasAnyAuthority(
                    RolesType.ADMIN.getName(),
                    RolesType.CUSTOMER.getName()
                )
            .requestMatchers(HttpMethod.GET, UserRoute.ALL_CHILDREN.getValue())
                .hasAnyAuthority(
                    RolesType.ADMIN.getName(),
                    RolesType.CUSTOMER.getName()
                )
            .requestMatchers(HttpMethod.POST, UserRoute.BASE.getValue())
                .hasAnyAuthority(
                    RolesType.ADMIN.getName(),
                    RolesType.CUSTOMER.getName()
                )
            .requestMatchers(HttpMethod.DELETE, UserRoute.ALL_CHILDREN.getValue())
                .hasAnyAuthority(
                    RolesType.ADMIN.getName(),
                    RolesType.CUSTOMER.getName()
                )
            .requestMatchers(HttpMethod.PUT, "/user/*/assign")
                .hasAnyAuthority(
                    RolesType.ADMIN.getName(),
                    RolesType.CUSTOMER.getName()
                )


            /*
             * Role definitions
             * */
            .requestMatchers(HttpMethod.POST, RoleRoute.BASE.getValue())
                .hasAnyAuthority(
                    RolesType.MANAGER.getName(),
                    RolesType.ADMIN.getName()
                )
            .requestMatchers(HttpMethod.POST, RoleRoute.ALL_CHILDREN.getValue())
                .hasAnyAuthority(
                    RolesType.MANAGER.getName(),
                    RolesType.ADMIN.getName()
                )
            .requestMatchers(HttpMethod.GET, RoleRoute.BASE.getValue())
                .hasAnyAuthority(
                    RolesType.MANAGER.getName(),
                    RolesType.ADMIN.getName()
                )
            .requestMatchers(HttpMethod.GET, RoleRoute.ALL_CHILDREN.getValue())
                .hasAnyAuthority(
                    RolesType.CUSTOMER.getName(),
                    RolesType.MANAGER.getName(),
                    RolesType.ADMIN.getName()
                )
            .requestMatchers(HttpMethod.DELETE, RoleRoute.ALL_CHILDREN.getValue())
                .hasAnyAuthority(
                    RolesType.MANAGER.getName(),
                    RolesType.ADMIN.getName()
                )

            /*
             * Resource definitions
             * */
            .requestMatchers(HttpMethod.POST, ResourceRoute.BASE.getValue())
                .hasAnyAuthority(
                    RolesType.MANAGER.getName(),
                    RolesType.ADMIN.getName()
                )
            .requestMatchers(HttpMethod.GET, ResourceRoute.BASE.getValue())
                .hasAnyAuthority(
                    RolesType.MANAGER.getName(),
                    RolesType.ADMIN.getName()
                )
            .requestMatchers(HttpMethod.GET, ResourceRoute.ALL_CHILDREN.getValue())
                .hasAnyAuthority(
                    RolesType.CUSTOMER.getName(),
                    RolesType.MANAGER.getName(),
                    RolesType.ADMIN.getName()
                ).
            requestMatchers(HttpMethod.PUT, ResourceRoute.ALL_CHILDREN.getValue())
                .hasAnyAuthority(
                    RolesType.MANAGER.getName(),
                    RolesType.ADMIN.getName()
                )
            .requestMatchers(HttpMethod.DELETE, ResourceRoute.ALL_CHILDREN.getValue())
                .hasAnyAuthority(
                    RolesType.MANAGER.getName(),
                    RolesType.ADMIN.getName()
                )

            /*
            * Permission definitions
            * */
            .requestMatchers(HttpMethod.POST, "/permission")
                .hasAnyAuthority(
                        RolesType.MANAGER.getName(),
                        RolesType.ADMIN.getName()
                )
            .requestMatchers(HttpMethod.POST, "/permission/**")
                .hasAnyAuthority(
                    RolesType.MANAGER.getName(),
                    RolesType.ADMIN.getName()
                )
        );
    }
}
