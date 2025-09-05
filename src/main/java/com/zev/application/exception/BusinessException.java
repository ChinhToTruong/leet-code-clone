package com.zev.application.exception;

import com.zev.application.common.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends Exception {
    private final String message;
    private String code;


    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(String code, String message) {
        this.message = message;
        this.code = code;
    }

    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        this.code = errorCode.getCode();
        this.message = message;
    }
}
