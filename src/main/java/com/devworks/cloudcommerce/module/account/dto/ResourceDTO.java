package com.devworks.cloudcommerce.module.account.dto;

import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.utils.Validator;
import com.devworks.cloudcommerce.module.account.model.Department;
import com.devworks.cloudcommerce.module.account.model.Action;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jdk.jfr.BooleanFlag;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) for resources.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceDTO {
    /**
     * Base Attributes
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

    @NotEmpty(message = "attribute path is required")
    private String path;

    @NotNull(message = "attribute active is required")
    @BooleanFlag
    private boolean active;

    @NotEmpty(message = "attribute permissions[] is required")
    private Set<Action> actions;

    /**
     * Defines the path to the resource.
     *
     * @param path The path to be defined.
     * @throws BadRequestException If the path is not valid.
     */
    public void setPath(String path) {
        if(!Validator.isValidPath(path))
            throw new BadRequestException(path + " is a invalid path");
        this.path = path;
    }
}