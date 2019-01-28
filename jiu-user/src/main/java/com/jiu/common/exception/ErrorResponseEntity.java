package com.jiu.common.exception;

import lombok.Data;

@Data
public class ErrorResponseEntity {

    private int code;
    private String message;

    public ErrorResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
