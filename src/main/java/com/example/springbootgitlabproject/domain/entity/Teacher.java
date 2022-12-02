package com.example.springbootgitlabproject.domain.entity;

import com.example.springbootgitlabproject.domain.dto.TeacherResponseDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    public static TeacherResponseDto of(Teacher teacher) {
        return TeacherResponseDto.builder().name(teacher.getName()).age(teacher.getAge()).build();
    }
}
