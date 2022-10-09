package com.example.school.dto;

import java.util.Set;

public record StudentDto(
        String id, String name, String surname, String email, Set<StudentInstructorDto> instructors
) {
}
