package com.example.school.dto.converter;

import com.example.school.dto.InstructorDto;
import com.example.school.model.Instructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class InstructorDtoConverter {
    private final InstructorStudentDtoConverter instructorStudentDtoConverter;

    public InstructorDtoConverter(InstructorStudentDtoConverter instructorStudentDtoConverter) {
        this.instructorStudentDtoConverter = instructorStudentDtoConverter;
    }

    public StudentInstructorDto convertToStudentInstructor(Optional<Instructor> from){
        return from
                .map(t-> new StudentInstructorDto(t.id(),t.name(),t.surname(),t.email(),t.department()))
                .orElse(null);
    }

    public InstructorDto convertToInstructorDto(Instructor from){
     return new InstructorDto(
             from.id(),
             from.name(),
             from.surname(),
             from.email(),
             from.department(),
             from.students().stream().map(instructorStudentDtoConverter::convert)
                     .collect(Collectors.toList());
     )
    }
}
