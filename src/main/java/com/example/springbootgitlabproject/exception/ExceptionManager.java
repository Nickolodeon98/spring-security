package com.example.springbootgitlabproject.exception;

import com.example.springbootgitlabproject.domain.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(TeacherException.class)
    public ResponseEntity<?> teacherExceptionHandler(TeacherException e) {
        return ResponseEntity.status(e.getErrorcode()
                .getStatus())
                .body(Response.error(e.getErrorcode().getMessage()));
    }
}
