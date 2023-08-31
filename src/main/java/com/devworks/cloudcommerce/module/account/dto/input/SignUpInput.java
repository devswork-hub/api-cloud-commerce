package com.devworks.cloudcommerce.module.account.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpInput {
    @NotEmpty(message = "attribute first_name is required")
    @JsonProperty("first_name")
    @Size(min = 2, message = "firstName should have at least 2 characters")
    private String firstName;

    @NotEmpty(message = "attribute last_name is required")
    @JsonProperty("last_name")
    private String lastName;

    @NotEmpty(message = "attribute email is required")
    @Email
    private String email;

    @NotEmpty(message = "attribute password is required")
    private String password;
}
