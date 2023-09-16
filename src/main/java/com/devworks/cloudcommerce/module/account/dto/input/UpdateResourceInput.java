package com.devworks.cloudcommerce.module.account.dto.input;

import com.devworks.cloudcommerce.module.account.model.Action;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record UpdateResourceInput(
    @NotEmpty(message = "attribute action is required")
    Set<Action> actions
) { }
