package com.devworks.cloudcommerce.common.exceptions.handlers;

import com.devworks.cloudcommerce.common.exceptions.NotFoundException;
import com.devworks.cloudcommerce.common.utils.Regex;
import com.devworks.cloudcommerce.common.exceptions.BadRequestException;
import com.devworks.cloudcommerce.common.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class ValidationHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto<Map<String, String>>> methodArgumentNotValidException(MethodArgumentNotValidException m) {
        Map<String, String> errorMessages = new LinkedHashMap<>();
        m.getBindingResult().getAllErrors().forEach(err -> {
            String field = Regex.camelToSnake(((FieldError) err).getField());
            String message = err.getDefaultMessage();
            errorMessages.put(field, message);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto
            .<Map<String, String>>builder()
            .messages(errorMessages)
            .httpStatus(HttpStatus.BAD_REQUEST)
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> badRequestException(BadRequestException b) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto
            .builder()
            .messages(b.getMessage())
            .httpStatus(HttpStatus.BAD_REQUEST)
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .build()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> httpMessageNotReadableException(HttpMessageNotReadableException h) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto
            .builder()
            .messages(h.getMessage())
            .httpStatus(HttpStatus.BAD_REQUEST)
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .build()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> notFoundException(NotFoundException b) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDto
            .builder()
            .messages(b.getMessage())
            .httpStatus(HttpStatus.NOT_FOUND)
            .statusCode(HttpStatus.NOT_FOUND.value())
            .build()
        );
    }
}