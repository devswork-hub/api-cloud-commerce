package com.devworks.cloudcommerce.module.account.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record LoginInput(
    @NotEmpty(message = "attribute email is required")
    @Email
    String email,

    @NotEmpty(message = "attribute password is required")
    String password
) {}


