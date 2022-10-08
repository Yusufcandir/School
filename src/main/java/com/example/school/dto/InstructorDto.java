package com.example.school.dto;

import java.util.List;

public record InstructorDto(
        String id, String name,String surname, String email, List<InstructorStudentDto> students
) {
}
