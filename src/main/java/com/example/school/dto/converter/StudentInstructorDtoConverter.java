package com.example.school.dto.converter;

import com.example.school.dto.StudentInstructorDto;
import com.example.school.model.Instructor;
import org.springframework.stereotype.Component;

@Component
public class StudentInstructorDtoConverter {

    public StudentInstructorDto convert(Instructor from){
        return new StudentInstructorDto(from.id(), from.name(), from.surname(), from.email(), from.department());
    }
}
