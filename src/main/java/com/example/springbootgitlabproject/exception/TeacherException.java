package com.example.springbootgitlabproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeacherException extends RuntimeException{

    private ErrorCode errorcode;

    private String message;

    @Override
    public String toString() {
        if (errorcode.getMessage() == null) return this.message;
        return String.format("%s %s", errorcode.getMessage(), this.message);
    }
}
