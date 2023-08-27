package com.devworks.cloudcommerce.common.settings.routing;

import com.devworks.cloudcommerce.module.user.routing.routes.UserRoute;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class UserModuleRouting {
    @Bean
    public SecurityFilterChain userRoutingChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, UserRoute.DEFAULT.getValue()).permitAll()
            .requestMatchers(HttpMethod.GET, UserRoute.DEFAULT.getValue()).permitAll()
            .requestMatchers(HttpMethod.GET, UserRoute.USER_ALL_ROUTES_CHILD.getValue()).permitAll()
            .requestMatchers(HttpMethod.DELETE, UserRoute.USER_ALL_ROUTES_CHILD.getValue()).permitAll());
        return http.build();
    }
}
