package com.devworks.cloudcommerce.module.account.routing.routes;

public enum UserRoute {
    BASE("/user"),
    ALL_CHILDREN("/user/*");

    private final String value;

    UserRoute(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
