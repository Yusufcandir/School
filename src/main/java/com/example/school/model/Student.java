package com.example.school.model;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;

import java.util.Set;
@Entity
@Data
public class Student {
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        String id;

        String name;
        String surname;
        String email;
        @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
        Set<Instructor> instructors;




        public Student(String name, String surname, String email,Set<Instructor> instructor) {
                this.name = name;
                this.surname = surname;
                this.email = email;
                this.instructors=instructor;

        }



        public Student() {

        }
}
