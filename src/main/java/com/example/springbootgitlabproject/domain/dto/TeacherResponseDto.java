package com.example.springbootgitlabproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class TeacherResponseDto {

    private String name;
    private int age;

}
