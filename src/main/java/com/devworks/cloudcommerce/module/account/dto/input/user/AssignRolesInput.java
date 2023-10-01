package com.devworks.cloudcommerce.module.account.dto.input.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public record AssignRolesInput(
    @NotNull(message = "attribute roles_ids is required")
    @JsonProperty("roles_ids")
    Set<UUID> roles
) {
}
