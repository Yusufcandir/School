package com.example.school.controller;

import com.example.school.dto.CourseDto;
import com.example.school.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/course")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping("{/courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable String courseId){
        return ResponseEntity.ok(service.getCourseById(courseId));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses(){
        return ResponseEntity.ok(service.getAllCourses());
    }
}
