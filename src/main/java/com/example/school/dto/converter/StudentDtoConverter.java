package com.example.school.dto.converter;

import com.example.school.dto.StudentDto;
import com.example.school.model.Student;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StudentDtoConverter {
    private final InstructorDtoConverter instructorDtoConverter;


    public StudentDtoConverter(InstructorDtoConverter instructorDtoConverter) {
        this.instructorDtoConverter = instructorDtoConverter;

    }

    public StudentDto convert(Student from){
        return new StudentDto(
                from.id(), from.name(), from.surname(), from.email(),
                instructorDtoConverter.convertToStudentInstructor(Optional.ofNullable(from.instructors() ))

        )
    }
}
