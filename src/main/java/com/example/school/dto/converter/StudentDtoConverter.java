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
                from.id(),
                from.name(),
                from.surname(),
                from.email(),
                from.instructors()
                        .stream().map(studentInstructorDtoConverter::convert)
                        .collect(Collectors.toSet())

        );
    }
}

