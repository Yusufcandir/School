package com.example.school.dto.converter;


import com.example.school.dto.StudentDto;
import com.example.school.model.Student;
import org.springframework.stereotype.Component;


@Component
public class StudentDtoConverter {

private final InstructorDtoConverter converter;
private final InstructorStudentDtoConverter instructorStudentDtoConverter;

    public StudentDtoConverter(InstructorDtoConverter converter,InstructorStudentDtoConverter instructorStudentDtoConverter) {
        this.converter = converter;
        this.instructorStudentDtoConverter = instructorStudentDtoConverter;

    }


    public StudentDto convert(Student from){
        return new StudentDto(
                from.id(),
                from.name(),
                from.surname(),
                from.email(),



        )
    }
}
