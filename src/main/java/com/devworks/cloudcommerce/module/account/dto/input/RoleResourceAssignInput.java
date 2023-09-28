package com.devworks.cloudcommerce.module.account.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.util.UUID;

public record RoleResourceAssignInput(
    @NotEmpty(message = "attribute resources_ids is required")
    @JsonProperty("resources_ids")
    Set<UUID> resourcesIds) {
}
