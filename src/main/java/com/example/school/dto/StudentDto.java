package com.example.school.dto;

import com.example.school.model.UserRole;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class StudentDto {
    @Id
    private String id;
    private String name;
    private String surname;
    private String email;

    private String password;
    private Boolean locked=false;
    private Boolean enabled=false;
    @Enumerated(EnumType.STRING)
    UserRole userRole;
    @OneToMany
    private Set<StudentInstructorDto> instructors;


    public StudentDto(String id, String name, String surname, String email, Set<StudentInstructorDto> instructors) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.instructors = instructors;
    }


    public StudentDto() {

    }
}