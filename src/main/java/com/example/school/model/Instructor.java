package com.example.school.model;


import com.example.school.enumuration.Department;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;

import java.util.Set;
//
//public record Instructor (
//        @Id
//        @GeneratedValue(generator = "UUID" )
//        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//        String id,
//        String name,
//        String surname,
//        String email,
//        Department department,
//        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "instructor")
//        Set<Student> students
//
//
//
//
//
//
//){
//
//        public Instructor(String name, String surname, String email, Department department, Set<Student> students) {
//                this(null, name, surname, email, department, students);
//        }
//}

@Entity
@Data
public class Instructor {
        @Id
        @GeneratedValue(generator = "UUID" )
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        String id;
        String name;
        String surname;
        String email;
        Department department;
        @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
        Set<Student> students;

        public Instructor(String name, String surname, String email, Department department, Set<Student> students) {
                this.name = name;
                this.surname = surname;
                this.email = email;
                this.department = department;
                this.students = students;
        }

        public Instructor(String id, String name, String surname, String email, Department department, Set<Student> students) {
                this.id = id;
                this.name = name;
                this.surname = surname;
                this.email = email;
                this.department = department;
                this.students = students;
        }

        public Instructor() {

        }
}