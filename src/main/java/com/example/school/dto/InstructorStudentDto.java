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
        private Boolean locked;
        private Boolean enabled;
        @Enumerated(EnumType.STRING)
        UserRole userRole;





        public InstructorStudentDto() {

        }

        public InstructorStudentDto(String name, String surname, String email, String password, UserRole userRole) {
                this.name = name;
                this.surname = surname;
                this.email = email;
                this.password = password;
                this.userRole = userRole;
                this.locked=false;
                this.enabled=true;
        }


}
