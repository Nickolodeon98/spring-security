package com.example.springbootgitlabproject.domain.dto;

import com.example.springbootgitlabproject.domain.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@Getter
public class ReviewCreateDto {

    private String title;
    private String content;


}
