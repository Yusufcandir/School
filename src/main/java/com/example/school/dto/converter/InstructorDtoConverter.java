package com.example.school.dto.converter;

import com.example.school.dto.InstructorDto;
import com.example.school.model.Instructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class InstructorDtoConverter {




    public InstructorDto convertToInstructorDto(Instructor from){
     return new InstructorDto(
             from.id(),
             from.name(),
             from.surname(),
             from.email(),
             from.department());
    }
}
