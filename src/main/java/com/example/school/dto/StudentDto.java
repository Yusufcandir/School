package com.example.school.dto;

import com.example.school.model.Course;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

//public record StudentDto(
//        String id, String name, String surname, String email, Set<StudentInstructorDto> instructors
//) {
//}
@Entity
@Data
public class StudentDto{
    @Id
    String id;
    String name;
    String surname;
    String email;
    @OneToMany
    Set<StudentInstructorDto> instructors;

    @OneToOne
    Course course;

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