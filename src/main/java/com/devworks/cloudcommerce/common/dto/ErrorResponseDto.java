package com.devworks.cloudcommerce.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponseDto<T> {
    private T messages;

    @JsonProperty("http_status")
    private HttpStatus httpStatus;

    @JsonProperty("status_code")
    private Integer statusCode;
}