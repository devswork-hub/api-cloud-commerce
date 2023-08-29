package com.devworks.cloudcommerce.module.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDto {
    @Null(message = "attribute id most be null")
    private UUID id;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @NotEmpty(message = "attribute name is required")
    @Size(min = 3, message = "firstName should have at least 3 characters")
    private String name;

    @NotEmpty(message = "attribute description is required")
    @Size(min = 10, message = "firstName should have at least 10 characters")
    private String description;
}
