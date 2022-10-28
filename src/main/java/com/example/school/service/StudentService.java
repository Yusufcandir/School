package com.example.school.service;

import com.example.school.dto.StudentDto;
import com.example.school.dto.converter.StudentDtoConverter;
import com.example.school.exception.InstructorNotFoundException;
import com.example.school.exception.StudentNotFoundException;
import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentDtoConverter converter;

    public StudentService(StudentRepository studentRepository, StudentDtoConverter converter) {
        this.studentRepository = studentRepository;
        this.converter = converter;
    }

    public Student findStudentById(String id){
        return studentRepository.findById(id).orElseThrow(
                () ->   new StudentNotFoundException("Student could not found by id: " + id));
    }

    public StudentDto getStudentById(String id){
      return converter.convert(findStudentById(id));
    }

    public List<StudentDto> getAllStudents(){
        return studentRepository.findAll().stream()
                .map(converter::convert).collect(Collectors.toList());
    }

    public void save(Student student){
        studentRepository.save(student);
    }

    public void update(Student student,String id){
        Optional<Student> exits= studentRepository.findById(id);
        if (exits.isPresent()){
            studentRepository.save(student);
        }
        else {
            throw new StudentNotFoundException("There is no such a student with id: "+ id);
        }
    }

    public void delete(String id){
        boolean exits = studentRepository.existsById(id);
        if (!exits){
            throw new InstructorNotFoundException("There is no such a student with id: "+ id);
        }
        studentRepository.deleteById(id);
    }










}
