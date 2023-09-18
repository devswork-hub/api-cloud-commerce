package com.devworks.cloudcommerce.module.account.dto.input;

import com.devworks.cloudcommerce.module.account.model.Action;
import com.devworks.cloudcommerce.module.account.model.Department;

import java.util.Set;

public record UpdateResourceInput(
    String name,
    boolean active,
    Set<Action> actions,
    Set<Department> departments
) { }
