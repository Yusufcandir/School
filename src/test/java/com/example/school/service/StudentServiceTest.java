package com.example.school.service;

import com.example.school.dto.converter.StudentDtoConverter;

import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

class StudentServiceTest  {


    private StudentService service;

    private StudentRepository repository;

    private StudentDtoConverter converter;

    @BeforeEach
    public void setUp(){
       repository=mock(StudentRepository.class);
       converter=mock(StudentDtoConverter.class);
       service= new StudentService(repository,converter);
    }

    @Test
    public void whenStudentIdExits_findStudentById_shouldReturnStudent(){
        Student student= new Student("id","name","surname","email", Set.of());

        Mockito.when(repository.findById("id")).thenReturn(Optional.of(student));

        Student result= service.findStudentById("id");
        Assertions.assertEquals(result, student);
    }



}