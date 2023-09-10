package com.devworks.cloudcommerce.module.account.dto;

import com.devworks.cloudcommerce.module.account.constants.PermissionTypes;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionDto {
    @Null(message = "attribute id most be null")
    private UUID id;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @NotEmpty(message = "attribute name is required")
    private PermissionTypes name;

    @NotEmpty(message = "attribute resource_id is required")
    @JsonProperty("resource_id")
    private UUID resourceId;
}
