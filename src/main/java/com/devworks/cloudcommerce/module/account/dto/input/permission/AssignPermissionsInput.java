package com.devworks.cloudcommerce.module.account.dto.input.permission;

import com.devworks.cloudcommerce.module.account.model.Action;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.util.UUID;

public record AssignPermissionsInput(
    @NotEmpty(message = "attribute role_id is required")
    @JsonProperty("role_id")
    UUID roleUUID,

    @NotEmpty(message = "attribute resource_id is required")
    @JsonProperty("resource_id")
    UUID resourceUUID,

    @NotEmpty(message = "attribute actions is required")
    @JsonProperty("actions")
    Set<Action> actions
) {}
