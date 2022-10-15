package com.example.school.service;

import com.example.school.dto.converter.StudentDtoConverter;
import com.example.school.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

class StudentServiceTest  {

    @Autowired
    private StudentService service;
    @Autowired
    private StudentRepository repository;
    @Autowired
    private StudentDtoConverter converter;

    @BeforeEach
    public void setUp(){
       repository=mock(StudentRepository.class);
       converter=mock(StudentDtoConverter.class);
       service= new StudentService(repository,converter);
    }



}