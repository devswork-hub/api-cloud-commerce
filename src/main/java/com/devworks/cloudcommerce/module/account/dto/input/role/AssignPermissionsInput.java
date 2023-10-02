package com.devworks.cloudcommerce.module.account.dto.input.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public record AssignPermissionsInput(
    @NotNull(message = "attribute permissions_ids is required")
    @JsonProperty("permissions_ids")
    Set<UUID> permissions
) { }
