package com.example.school.dto.converter;

import com.example.school.dto.CourseStudentDto;
import com.example.school.model.Student;
import org.springframework.stereotype.Component;

@Component
public class CourseStudentDtoConverter {
    public CourseStudentDto convert(Student from){
        return new CourseStudentDto(from.getId(),from.getName(),from.getSurname(),from.getEmail());
    }
}
