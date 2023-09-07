package com.devworks.cloudcommerce.module.account.dto;

import com.devworks.cloudcommerce.module.account.constants.AccountStatusTypes;
import com.devworks.cloudcommerce.module.account.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCredentialsDto {
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @NotEmpty(message = "attribute email is required")
    @Email
    private String email;

    @NotEmpty(message = "attribute username is required")
    @Size(min = 8, message = "username should have at least 8 characters")
    private String username;

    @NotEmpty(message = "attribute password_hash is required")
    private String passwordHash;

    @NotEmpty(message = "attribute password_salt is required")
    private String passwordSalt;

    private AccountStatusTypes accountStatus;
    private Set<Role> roles;
}
