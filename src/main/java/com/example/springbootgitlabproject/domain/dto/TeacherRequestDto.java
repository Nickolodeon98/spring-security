package com.example.springbootgitlabproject.domain.dto;

import com.example.springbootgitlabproject.domain.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AllArgsConstructor
@Getter
@Builder
public class TeacherRequestDto {

    private String userName;
    private String password;
    private String name;
    private int age;

    public Teacher toEntity(BCryptPasswordEncoder encoder) {
        return Teacher.builder()
                .userName(userName)
                .password(encoder.encode(password))
                .name(name)
                .age(age)
                .build();
    }
}
