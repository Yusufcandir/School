package com.example.school.dto;

import com.example.school.model.Department;

public record InstructorStudentDto(
        String id, String name, String surname, String email
        ) {
}
