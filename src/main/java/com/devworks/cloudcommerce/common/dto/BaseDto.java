package com.devworks.cloudcommerce.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public abstract class BaseDto {
    @Null(message = "attribute id most be null")
    private UUID id;

    @JsonProperty("created_at")
    @NotNull(message = "createdAt attribute must not be null")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @NotNull(message = "updatedAt attribute must not be null")
    private LocalDateTime updatedAt;
}
