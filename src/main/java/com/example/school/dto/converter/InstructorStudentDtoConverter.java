package com.example.school.dto.converter;

import com.example.school.dto.InstructorStudentDto;

import com.example.school.model.Student;
import org.springframework.stereotype.Component;


@Component
public class InstructorStudentDtoConverter {

    public InstructorStudentDto convert(Student from){
        return new InstructorStudentDto(from.getId(), from.getName(), from.getSurname(), from.getEmail());
    }


}
