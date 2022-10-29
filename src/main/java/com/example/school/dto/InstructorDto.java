package com.example.school.dto;


import com.example.school.model.Department;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Data

public class InstructorDto {
    @Id
    private String id;
    private String name;
    private String surname;
    private String email;


    @Enumerated(EnumType.STRING)
    private Department department;


    public InstructorDto(String id, String name, String surname, String email, Department department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.department = department;

    }

    public InstructorDto() {

    }
}