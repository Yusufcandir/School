package com.example.school.dto;


import com.example.school.model.Department;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Data
public class StudentInstructorDto {
    @Id
    String id;
    String name;
    String surname;
    String email;
    @Enumerated(EnumType.STRING)
    Department department;

    public StudentInstructorDto(String id, String name, String surname, String email, Department department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.department = department;
    }

    public StudentInstructorDto() {

    }
}