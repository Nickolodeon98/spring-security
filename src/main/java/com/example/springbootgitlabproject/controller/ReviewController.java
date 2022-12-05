package com.example.springbootgitlabproject.controller;

import com.example.springbootgitlabproject.domain.dto.ReviewCreateDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @PostMapping("/new")
    public String write(@RequestBody ReviewCreateDto reviewCreateDto) {
        return "";
    }
}
