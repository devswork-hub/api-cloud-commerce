package com.devworks.cloudcommerce.common.exceptions.handlers;

import com.devworks.cloudcommerce.common.dto.ErrorResponseDto;
import com.devworks.cloudcommerce.common.exceptions.CustomAuthenticationException;
import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomHandlers {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto<Object>> badRequestException(BadRequestException b) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto
            .builder()
            .messages(b.getMessage())
            .httpStatus(HttpStatus.BAD_REQUEST)
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .build()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto<Object>> notFoundException(NotFoundException b) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDto
            .builder()
            .messages(b.getMessage())
            .httpStatus(HttpStatus.NOT_FOUND)
            .statusCode(HttpStatus.NOT_FOUND.value())
            .build()
        );
    }

    @ExceptionHandler(CustomAuthenticationException.class)
    public ResponseEntity<ErrorResponseDto<Object>> authenticationException(CustomAuthenticationException a) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponseDto
            .builder()
            .messages(a.getMessage())
            .httpStatus(HttpStatus.FORBIDDEN)
            .statusCode(HttpStatus.FORBIDDEN.value())
            .build()
        );
    }
}
