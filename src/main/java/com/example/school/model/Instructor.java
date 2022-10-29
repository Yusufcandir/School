package com.example.school.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;

@Entity
@Data
public class Instructor {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private String surname;
    private String email;
    @Enumerated(EnumType.STRING)
    Department department;

    public Instructor(String name, String surname, String email, Department department) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.department = department;

    }

    public Instructor(String id, String name, String surname, String email, Department department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.department = department;
    }

    public Instructor() {

    }
}