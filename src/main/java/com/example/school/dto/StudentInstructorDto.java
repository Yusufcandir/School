package com.example.school.dto;

import com.example.school.model.Department;

public record StudentInstructorDto(
        String id, String name, String surname, String email, Department department
){

}
