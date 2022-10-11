package com.example.school.dto;

import com.example.school.model.Student;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class CourseStudentDto {
    @Id
    String id;
    String name;
    String surname;
    String email;

    public CourseStudentDto(String id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public CourseStudentDto() {

    }
}
