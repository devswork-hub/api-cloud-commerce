package com.devworks.cloudcommerce.module.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto extends BaseDto{
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String email;

    private String cpf;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("phone_country_code")
    private String phoneCountryCode;

    @JsonProperty("phone_code_area")
    private String phoneCodeArea;
}
