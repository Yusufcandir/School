package com.example.school.dto;

import com.example.school.model.Department;

import java.util.List;

public record InstructorDto(
        String id, String name, String surname, String email, Department department
) {
}
