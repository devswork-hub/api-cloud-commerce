package com.devworks.cloudcommerce.module.user.routing.routes;

public enum RoleRoute {
    DEFAULT("/role"),
    ALL_ROUTES_CHILD("/role/*");

    private final String value;

    RoleRoute(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
