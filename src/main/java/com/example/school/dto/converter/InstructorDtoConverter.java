package com.example.school.dto.converter;

import com.example.school.dto.InstructorDto;

import com.example.school.model.Instructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class InstructorDtoConverter {


    private final InstructorStudentDtoConverter instructorStudentDtoConverter;

    public InstructorDtoConverter(InstructorStudentDtoConverter instructorStudentDtoConverter) {
        this.instructorStudentDtoConverter = instructorStudentDtoConverter;
    }



    public InstructorDto convertToInstructorDto(Instructor from){
     return new InstructorDto(
             from.getId(),
             from.getName(),
             from.getSurname(),
             from.getEmail(),
             from.getDepartment(),
             from.getStudents()
                     .stream()
                     .map(instructorStudentDtoConverter::convert).collect(Collectors.toSet())
     );
    }
}
