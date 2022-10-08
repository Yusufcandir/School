package com.example.school.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public record Instructor (
        @Id
        @GeneratedValue(generator = "UUID" )
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        String id,
        String name,
        String surname,
        String email,
        Department department






){

}
