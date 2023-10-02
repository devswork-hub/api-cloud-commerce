package com.devworks.cloudcommerce.module.account.dto.input.permission;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateInput(
    @NotNull(message = "attribute resource_id is required")
    @JsonProperty("resource_id")
    UUID resourceId,

    @NotNull(message = "attribute action_id is required")
    @JsonProperty("action_id")
    UUID actionId
) {}
