package com.example.school;

import com.example.school.enumuration.Department;
import com.example.school.model.Instructor;
import com.example.school.model.Student;
import com.example.school.repository.InstructorRepository;
import com.example.school.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Set;

@SpringBootApplication

// could not find those classes,so I move StudentApplication class under the model package
//@EntityScan(basePackages = {"com.example.school.model"})

public class SchoolApplication implements CommandLineRunner {
private final StudentRepository studentRepository;
private final InstructorRepository instructorRepository;

    public SchoolApplication(StudentRepository studentRepository, InstructorRepository instructorRepository) {
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Student student1= studentRepository.
                save(new Student("Yusuf", "Candır", "yusuf@gmail.com"));
        Instructor instructor1= instructorRepository.
                save(new Instructor("Zeynep", "Gültekin", "zeynep@gmail.com",
                        Department.ArtHistory, Set.of(student1)));

        System.out.println(student1);
        System.out.println(instructor1);
    }
}
