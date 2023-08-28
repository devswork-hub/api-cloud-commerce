package com.devworks.cloudcommerce.module.account.dto;

import com.devworks.cloudcommerce.module.account.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCredentialsDto {
    private UUID id;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, message = "username should have at least 8 characters")
    private String username;

    @NotEmpty
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;

    private List<Role> roles;
}
