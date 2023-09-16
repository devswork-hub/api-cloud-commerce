package com.devworks.cloudcommerce.module.account.routing.routes;

public enum ResourceRoute {
    BASE("/resource"),
    ALL_CHILDREN("/resource/*");

    private final String value;

    ResourceRoute(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
