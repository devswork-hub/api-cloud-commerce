package com.devworks.cloudcommerce.module.account.dto;

import com.devworks.cloudcommerce.module.account.constants.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    @Null(message = "attribute id most be null")
    private UUID id;

    @Builder.Default
    @JsonProperty("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @NotEmpty
    @JsonProperty("first_name")
    @Size(min = 2, message = "firstName should have at least 2 characters")
    private String firstName;

    @NotEmpty
    @JsonProperty("last_name")
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @CPF
    private String cpf;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("phone_country_code")
    private String phoneCountryCode;

    @JsonProperty("phone_code_area")
    private String phoneCodeArea;

    @JsonProperty("user_type")
    private UserType userType;
}
