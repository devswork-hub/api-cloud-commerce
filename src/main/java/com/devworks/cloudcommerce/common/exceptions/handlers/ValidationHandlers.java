package com.devworks.cloudcommerce.common.exceptions.handlers;

import com.devworks.cloudcommerce.common.utils.Converter;
import com.devworks.cloudcommerce.common.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;

@RestControllerAdvice
public class ValidationHandlers {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto<Map<String, String>>> methodArgumentNotValidException(MethodArgumentNotValidException m) {
        Map<String, String> errorMessages = new LinkedHashMap<>();
        m.getBindingResult().getAllErrors().forEach(err -> {
            String field = Converter.fromCamelToSnake(((FieldError) err).getField());
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


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDto<Object>> missingServletRequestParameterException(MissingServletRequestParameterException m) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto
            .builder()
            .messages(m.getMessage())
            .httpStatus(HttpStatus.BAD_REQUEST)
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .build()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto<Object>> httpMessageNotReadableException(HttpMessageNotReadableException h) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto
            .builder()
            .messages(h.getMessage())
            .httpStatus(HttpStatus.BAD_REQUEST)
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .build()
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto<Object>> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException h) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto
            .builder()
            .messages(h.getMessage())
            .httpStatus(HttpStatus.BAD_REQUEST)
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .build()
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto<Object>> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException h) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(ErrorResponseDto
            .builder()
            .messages(h.getMessage())
            .httpStatus(HttpStatus.METHOD_NOT_ALLOWED)
            .statusCode(HttpStatus.METHOD_NOT_ALLOWED.value())
            .build()
        );
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ErrorResponseDto<Object>> missingPathVariableException(MissingPathVariableException m) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(ErrorResponseDto
                .builder()
                .messages(m.getMessage())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build()
        );
    }
}