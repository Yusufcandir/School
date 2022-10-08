package com.example.school.dto;

import com.example.school.model.Instructor;

import java.util.List;

public record StudentDto(
        String id,String name,String surname, String email,List<InstructorStudentDto> students
) {
}
