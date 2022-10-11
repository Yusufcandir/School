package com.example.school.dto.converter;

import com.example.school.dto.CourseDto;
import com.example.school.model.Course;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CourseDtoConverter {
    private final CourseStudentDtoConverter converter;

    public CourseDtoConverter(CourseStudentDtoConverter converter) {
        this.converter = converter;
    }

    public CourseDto convert(Course from){
        return new CourseDto(from.getId(),from.getStudent()
                .stream().map(converter::convert).collect(Collectors.toSet()));
    }
}
