package com.devworks.cloudcommerce.module.account.dto;

import com.devworks.cloudcommerce.module.account.model.Resource;
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
public class GroupDto {
//    @Null(message = "attribute id most be null")
    private UUID id;

    @NotEmpty(message = "attribute name is required")
    private String name;

    private int priority;

    @NotEmpty(message = "attribute resources [] is required")
    private Set<Resource> resources;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
