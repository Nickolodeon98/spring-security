package com.example.springbootgitlabproject.controller;

import com.example.springbootgitlabproject.domain.Response;
import com.example.springbootgitlabproject.domain.dto.TeacherRequestDto;
import com.example.springbootgitlabproject.domain.dto.TeacherResponseDto;
import com.example.springbootgitlabproject.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
public class TeacherController {

//    @Autowired
//    TeacherService teacherService; // 최근 스프링에서는 @Autowired 대신 private final 로 싱글톤 패턴을 구성한다.

    private final TeacherService teacherService; // 생성자가 @RequiredArgsConstructor 를 이용해 생성되었다.

    @GetMapping
    public String get() {
        return "Hello";
    }

    @PostMapping("/test")
    public Response<TeacherResponseDto> register(@RequestBody TeacherRequestDto teacherRequestDto) {
        TeacherResponseDto teacherResponseDto = teacherService.addTeacherInfo(teacherRequestDto);
        return Response.success(teacherResponseDto);
    }

    @PostMapping("/login")
    public Response<String> login(@RequestBody TeacherRequestDto teacherRequestDto) {
        String token = teacherService.promptToken(teacherRequestDto.getUserName(), teacherRequestDto.getPassword());
        return Response.success(token);
    }
}
