package com.devworks.cloudcommerce.module.account.constants;

import lombok.Getter;

@Getter
public enum UserType {
    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    CUSTOMER("CUSTOMER");

    private final String name;

    UserType(String name) {
        this.name = name;
    }
}
