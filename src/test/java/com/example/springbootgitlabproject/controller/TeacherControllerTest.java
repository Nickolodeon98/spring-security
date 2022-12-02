package com.example.springbootgitlabproject.controller;

import com.example.springbootgitlabproject.domain.Response;
import com.example.springbootgitlabproject.domain.dto.TeacherRequestDto;
import com.example.springbootgitlabproject.domain.dto.TeacherResponseDto;
import com.example.springbootgitlabproject.service.TeacherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

    @MockBean
    TeacherService teacherService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("로그인에 성공한다.")
    void login_success() throws Exception {

        TeacherRequestDto teacherRequestDto = TeacherRequestDto.builder()
                .userName("sjeon0730")
                .password("abcdefgh")
                .build();

        given(teacherService.promptToken(any(), any()))
                .willReturn("token");

        String url = "/api/v1/teachers/login";
        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(teacherRequestDto)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(teacherService).promptToken(teacherRequestDto.getUserName(), teacherRequestDto.getPassword());
    }

    @Test
    @DisplayName("로그인에 실패한다.")
    void login_fail() {

    }
//    @Test
//    @DisplayName("성공 응답을 올바르게 한다.")
//    void success_test() throws Exception {
//
//        String name = "전승환";
//        int age = 25;
//
//        TeacherResponseDto teacherResponseDto = TeacherResponseDto.builder()
//                .name("전승환").age(25).build();
//
//        given(teacherService.addTeacherInfo(name, age)).willReturn(teacherResponseDto);
//
//        String url = "/api/v1/teachers/test";
//        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
//                .param(name).param(String.valueOf(age)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.resultCode").value("SUCCESS"))
//                .andExpect(jsonPath("$.resultCode.name").value("전승환"))
//                .andExpect(jsonPath("$.resultCode.age").value(25))
//                .andDo(print());
//
//        verify(teacherService).addTeacherInfo(name, age);
//    }

}