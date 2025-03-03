package com.devworks.cloudcommerce.module.account.dto;

import com.devworks.cloudcommerce.module.account.constants.AccountStatusType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
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
public class CredentialsDTO {
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

    @NotEmpty(message = "attribute user_id is required")
    private UUID userId;

    /**
     * Optional Attributes
     */
    @Size(min = 8, message = "username should have at least 8 characters")
    private String username;

    @JsonProperty("account_status")
    private AccountStatusType accountStatus;
}
