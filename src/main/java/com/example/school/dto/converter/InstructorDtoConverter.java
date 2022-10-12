package com.example.school.dto.converter;

import com.example.school.dto.InstructorDto;

import com.example.school.model.Instructor;
import org.springframework.stereotype.Component;

@Component
public class InstructorDtoConverter {




    public InstructorDto convertToInstructorDto(Instructor from){
     return new InstructorDto(
             from.getId(),
             from.getName(),
             from.getSurname(),
             from.getEmail(),
             from.getDepartment()
     );
    }
}
