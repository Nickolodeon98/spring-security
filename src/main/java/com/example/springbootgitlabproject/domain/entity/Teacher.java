package com.example.springbootgitlabproject.domain.entity;

import com.example.springbootgitlabproject.domain.UserRole;
import com.example.springbootgitlabproject.domain.dto.TeacherResponseDto;
import lombok.*;

import javax.persistence.*;

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

    private String userName;
    private String password;
    private String name;
    private int age;

    @Enumerated(EnumType.STRING) // 의미하는 것이 무엇인지?
    private UserRole userRole;

    public static TeacherResponseDto of(Teacher teacher) {
        return TeacherResponseDto.builder().name(teacher.getName()).age(teacher.getAge()).build();
    }
}
