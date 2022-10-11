package com.example.school.dto;



//public record InstructorStudentDto(
//        String id, String name, String surname, String email
//        ) {
//}

import com.example.school.model.Course;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class InstructorStudentDto{
        @Id
        String id;
        String name;
        String surname;
        String email;



        public InstructorStudentDto(String id, String name, String surname, String email) {
                this.id = id;
                this.name = name;
                this.surname = surname;
                this.email = email;
        }

        public InstructorStudentDto() {

        }
}
