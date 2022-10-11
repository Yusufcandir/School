package com.example.school.dto;

import com.example.school.enumeration.Department;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

//public record InstructorDto(
//        String id, String name, String surname, String email, Department department, Set<InstructorStudentDto> students
//) {
//}

@Entity
@Data
public class InstructorDto{
    @Id
    String id;
    String name;
    String surname;
    String email;
    Department department;
    @OneToMany
    Set<InstructorStudentDto> students;

    public InstructorDto(String id, String name, String surname, String email, Department department, Set<InstructorStudentDto> students) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.department = department;
        this.students = students;
    }

    public InstructorDto() {

    }
}