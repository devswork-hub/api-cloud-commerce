package com.devworks.cloudcommerce.module.account.routing.routes;


public enum RoleRoute {
    BASE("/role"),
    ALL_CHILDREN("/role/*");

    private final String value;

    RoleRoute(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
