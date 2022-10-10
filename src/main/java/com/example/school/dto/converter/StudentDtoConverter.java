package com.example.school.dto.converter;


import com.example.school.dto.StudentDto;
import com.example.school.model.Student;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class StudentDtoConverter {


private final StudentInstructorDtoConverter studentInstructorDtoConverter;

    public StudentDtoConverter(StudentInstructorDtoConverter studentInstructorDtoConverter) {
        this.studentInstructorDtoConverter = studentInstructorDtoConverter;
    }


    public StudentDto convert(Student from){
        return new StudentDto(
                from.getId(),
                from.getName(),
                from.getSurname(),
                from.getSurname(),
                from.getInstructors()
                        .stream().map(studentInstructorDtoConverter::convert)
                        .collect(Collectors.toSet())

        );
    }
}

