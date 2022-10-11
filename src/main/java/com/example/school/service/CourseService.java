package com.example.school.service;

import com.example.school.dto.CourseDto;

import com.example.school.dto.converter.CourseDtoConverter;
import com.example.school.exception.CourseNotFoundException;
import com.example.school.model.Course;

import com.example.school.repository.CourseRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository repository;
    private final CourseDtoConverter converter;

    public CourseService(CourseRepository repository, CourseDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public Course findCourseById(String id){
        return repository.findById(id).
                orElseThrow(()-> new CourseNotFoundException("Course could not found by id: "+ id));
    }


    public CourseDto getCourseById(String id){
        return converter.convert(findCourseById(id));
    }

    public List<CourseDto> getAllCourses(){
        return repository.findAll().stream()
                .map(converter::convert).collect(Collectors.toList());
    }
}
