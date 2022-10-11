package com.example.school.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class CourseDto {
    @Id
    String id;
    @OneToMany
    Set<CourseStudentDto> students;

    public CourseDto(String id, Set<CourseStudentDto> students) {
        this.id = id;
        this.students = students;
    }

    public CourseDto() {

    }


}
