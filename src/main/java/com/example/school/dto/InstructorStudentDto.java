package com.example.school.dto;



//public record InstructorStudentDto(
//        String id, String name, String surname, String email
//        ) {
//}

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





        public InstructorStudentDto() {

        }
}
