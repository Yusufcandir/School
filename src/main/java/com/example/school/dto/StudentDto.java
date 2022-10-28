package com.example.school.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class StudentDto{
    @Id
    String id;
    String name;
    String surname;
    String email;

    String password;
    @OneToMany
    Set<StudentInstructorDto> instructors;


    public StudentDto(String id, String name, String surname, String email,String password, Set<StudentInstructorDto> instructors) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password=password;
        this.instructors = instructors;
    }

    public StudentDto() {

    }
}