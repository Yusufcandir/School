package com.example.school;


import com.example.school.model.Department;
import com.example.school.model.Instructor;
import com.example.school.model.Student;
import com.example.school.repository.InstructorRepository;
import com.example.school.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication



public class SchoolApplication  {
//        implements CommandLineRunner
//private final StudentRepository studentRepository;
//private final InstructorRepository instructorRepository;
//
//    public SchoolApplication(StudentRepository studentRepository, InstructorRepository instructorRepository) {
//        this.studentRepository = studentRepository;
//        this.instructorRepository = instructorRepository;
//    }

    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        Instructor instructor1= instructorRepository.
//                save(new Instructor("Zeynep", "Gültekin", "zeynep@gmail.com",
//                        Department.Architecture));
//        Instructor instructor2= instructorRepository.
//                save(new Instructor("Yıldız", "Yılmaz", "yıldız@gmail.com",Department.Biology));
//
//
//        Student student1= studentRepository.
//                save(new Student("Yusuf", "Candır", "yusuf@gmail.com",Set.of(instructor1)));
//
//        Student student2=studentRepository.
//                save(new Student("Elif", "Başar", "elif@gmail.com",Set.of(instructor2)));
//
//
//        System.out.println(instructor1);
//        System.out.println(instructor2);
//        System.out.println(student1);
//        System.out.println(student2);
//    }
}
