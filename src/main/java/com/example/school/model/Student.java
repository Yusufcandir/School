package com.example.school.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

public record Student(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        String id,

        String name,
        String surname,
        String email,
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(
                name = "student_instructor_map",
                joinColumns = @JoinColumn(
                        name = "student_id",
                        referencedColumnName = "id"
                ),
                inverseJoinColumns = @JoinColumn(
                        name = "instructor_id",
                        referencedColumnName = "id"
                )

        )
        List<Instructor> instructors





) {
}
