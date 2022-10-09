package com.example.school.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Set;

public record Student(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        String id,

        String name,
        String surname,
        String email,
        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "student")
        @JoinColumn(name = "instructor_id", nullable = false)
        Set<Instructor> instructors





) {
}
