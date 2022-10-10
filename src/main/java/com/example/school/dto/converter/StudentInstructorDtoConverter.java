package com.example.school.dto.converter;

import com.example.school.dto.StudentInstructorDto;
import com.example.school.model.Instructor;
import org.springframework.stereotype.Component;

@Component
public class StudentInstructorDtoConverter {

    public StudentInstructorDto convert(Instructor from){
        return new StudentInstructorDto(from.getId(), from.getName(),
                from.getSurname(),from.getEmail(), from.getDepartment());
    }
}
