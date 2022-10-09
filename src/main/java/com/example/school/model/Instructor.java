package com.example.school.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Set;

public record Instructor (
        @Id
        @GeneratedValue(generator = "UUID" )
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        String id,
        String name,
        String surname,
        String email,
        Department department,
        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "instructor")
        Set<Student> students






){

}
