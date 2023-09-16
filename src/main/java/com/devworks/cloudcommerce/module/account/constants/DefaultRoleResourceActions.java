package com.devworks.cloudcommerce.module.account.constants;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
public enum DefaultRoleResourceActions {
    CUSTOMER_ACTIONS(ActionType.READ),
    ADMIN_ACTIONS(
        ActionType.READ,
        ActionType.WRITE,
        ActionType.DELETE
    ),
    MANAGER_ACTIONS(ActionType.allActionTypes().toArray(new ActionType[0]));

    private final Set<ActionType> actions;

    DefaultRoleResourceActions(ActionType... actions) {
        this.actions = new HashSet<>(Arrays.asList(actions));
    }
}