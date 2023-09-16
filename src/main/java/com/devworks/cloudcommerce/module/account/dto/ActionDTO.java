package com.devworks.cloudcommerce.module.account.dto;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.module.account.constants.ActionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jdk.jfr.BooleanFlag;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActionDTO {
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

    @NotNull(message = "attribute active is required")
    @BooleanFlag
    private boolean active;

    /**
     * Defines the name to the permission_type.
     *
     * @param name The name to be defined.
     * @throws BadRequestException If the name is not valid.
     */
    public void setName(String name) {
        try {
            this.name = String.valueOf(ActionType.valueOf(name));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid role type with name " + name);
        }
    }
}
