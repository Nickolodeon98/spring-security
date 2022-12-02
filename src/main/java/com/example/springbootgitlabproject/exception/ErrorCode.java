package com.example.springbootgitlabproject.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    DUPLICATE_ERROR(HttpStatus.BAD_REQUEST, "빈 값이 아닙니다."),
    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "아이디가 없습니다."),
    PASSWORD_ERROR(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다.");

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
