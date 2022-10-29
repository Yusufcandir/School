package com.example.school.dto;

import com.example.school.model.UserRole;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Data
public class InstructorStudentDto{
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





        public InstructorStudentDto() {

        }




}
