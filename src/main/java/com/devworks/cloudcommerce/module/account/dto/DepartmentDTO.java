package com.devworks.cloudcommerce.module.account.dto;

import com.devworks.cloudcommerce.module.account.model.Resource;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jdk.jfr.BooleanFlag;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDTO {
  /**
   * Internal Base Attributes
   */
  @Null(message = "attribute id most be null")
  private UUID id;

  @JsonProperty("created_at")
  private LocalDateTime createdAt;

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
   * Optional Attributes
   */
  private Set<Resource> resources;
}
