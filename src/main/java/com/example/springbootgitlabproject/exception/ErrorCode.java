package com.example.springbootgitlabproject.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    DUPLICATE_ERROR(HttpStatus.BAD_REQUEST, "빈 값이 아닙니다.");

    private final HttpStatus httpStatus;
    private final String message;


    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
