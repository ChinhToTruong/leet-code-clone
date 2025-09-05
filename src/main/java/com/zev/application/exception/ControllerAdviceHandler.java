package com.zev.application.exception;

import com.zev.application.common.ErrorCode;
import com.zev.application.config.Translator;
import com.zev.application.dto.response.DataResponse;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;
import java.util.*;

@RestControllerAdvice
@Slf4j
public class ControllerAdviceHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataResponse<?> handleBusinessException(BusinessException e) {
        return DataResponse.builder()
                .message(e.getMessage())
                .code(e.getCode())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataResponse<?> handleException(Exception e) {

        return DataResponse.builder()
                .message(e.getMessage())
                .code("500")
                .build();
    }

    @ExceptionHandler(AuthenticationException.class)
    public DataResponse<?> handleAuthenticationException(AuthenticationException e) {
        return DataResponse.builder()
                .code(ErrorCode.UNAUTHORIZED.getCode())
                .message(ErrorCode.UNAUTHORIZED.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DataResponse<?> handleValidationException(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return DataResponse.builder()
                .data(errors)
                .build();
    }

}
