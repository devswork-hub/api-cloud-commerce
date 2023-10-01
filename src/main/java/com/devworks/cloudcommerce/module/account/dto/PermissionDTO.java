package com.devworks.cloudcommerce.module.account.dto;

import com.devworks.cloudcommerce.module.account.model.Module;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionDTO {
    /**
     * Internal Base Attributes
     */
    @Null(message = "attribute id most be null")
    private UUID id;

    @JsonProperty("created_at")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    /**
     * Required Attributes
     */
    @Null(message = "attribute action_id most be null")
    @JsonProperty("action_id")
    private UUID actionId;

    @Null(message = "attribute resource_id most be null")
    @JsonProperty("resource_id")
    private UUID resourceId;

    @NotEmpty(message = "attribute modules is required")
    private Set<Module> modules;
}

