package com.example.springbootgitlabproject.repository;

import com.example.springbootgitlabproject.domain.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByName(String name);
}
