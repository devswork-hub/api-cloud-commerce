package com.devworks.cloudcommerce.module.account.dto.input;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.util.UUID;

public record RoleResourceCreateInput(

    @NotEmpty(message = "attribute resource_id is required")
    @org.hibernate.validator.constraints.UUID
    UUID resourceId,

    @NotEmpty(message = "attribute actions_ids is required")
    Set<UUID> actionsIds
) {
}
