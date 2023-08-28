package com.devworks.cloudcommerce.module.account.routing.routes;

public enum UserRoute {
    DEFAULT("/user"),
    USER_ALL_ROUTES_CHILD("/user/*");

    private final String value;

    UserRoute(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
