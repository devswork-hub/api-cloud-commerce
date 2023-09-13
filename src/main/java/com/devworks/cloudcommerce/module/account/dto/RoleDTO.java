package com.devworks.cloudcommerce.module.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import jdk.jfr.BooleanFlag;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {
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
    @NotEmpty(message = "attribute name is required")
    private String name;

    @NotEmpty(message = "attribute description is required")
    @Size(min = 10, message = "description should have at least 10 characters")
    private String description;

    @NotEmpty(message = "attribute active is required")
    @BooleanFlag
    private boolean active;
}
