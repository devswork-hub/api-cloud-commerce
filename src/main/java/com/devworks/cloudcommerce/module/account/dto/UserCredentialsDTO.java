package com.devworks.cloudcommerce.module.account.dto;

import com.devworks.cloudcommerce.module.account.constants.AccountStatusTypes;
import com.devworks.cloudcommerce.module.account.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCredentialsDTO {
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
    @NotEmpty(message = "attribute email is required")
    @Email
    private String email;

    @JsonProperty("password_hash")
    @NotEmpty(message = "attribute password_hash is required")
    private String passwordHash;

    @JsonProperty("password_salt")
    @NotEmpty(message = "attribute password_salt is required")
    private String passwordSalt;

    @JsonProperty("account_status")
    @NotEmpty(message = "attribute account_status is required")
    private AccountStatusTypes accountStatus;

    /**
     * Optional Attributes
     */
    @Size(min = 8, message = "username should have at least 8 characters")
    private String username;

    private Set<Role> roles;
}
