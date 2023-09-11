package com.devworks.cloudcommerce.module.account.dto;

import com.devworks.cloudcommerce.module.account.model.Permission;
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
public class ResourceDto {
//    @Null(message = "attribute id most be null")
    private UUID id;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @NotEmpty(message = "attribute name is required")
    private String name;

    // on create resource, validate if is valid link
    private String link;

    @JsonProperty("group_id")
    private UUID groupId;

    @JsonProperty("permissions")
    private Set<Permission> resourcePermissions;

}