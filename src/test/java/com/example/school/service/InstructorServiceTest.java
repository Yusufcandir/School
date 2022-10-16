package com.example.school.service;


import com.example.school.dto.InstructorDto;
import com.example.school.dto.converter.InstructorDtoConverter;
import com.example.school.exception.InstructorNotFoundException;
import com.example.school.model.Department;
import com.example.school.model.Instructor;
import com.example.school.repository.InstructorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

class InstructorServiceTest {
    private InstructorService service;
    private  InstructorRepository repository;
    private  InstructorDtoConverter converter;


    @BeforeEach
    public void setUp(){
        repository= Mockito.mock(InstructorRepository.class);
        converter=Mockito.mock(InstructorDtoConverter.class);
        service= new InstructorService(repository,converter);
    }

    @Test
    public void whenInstructorIdExits_findInstructorById_shouldReturnInstructor(){
        Instructor instructor= new Instructor("id","name","surname","email", Department.ArtHistory);

        Mockito.when(repository.findById("id")).thenReturn(Optional.of(instructor));

        Instructor result= service.findInstructorById("id");
        assertEquals(result,instructor);
    }

    @Test
    public void whenInstructorIdDoesNotExits_findInstructorById_shouldThrowInstructorNotFoundException(){

        Mockito.when(repository.findById("id")).thenReturn(Optional.empty());

        assertThrows(InstructorNotFoundException.class,()-> service.findInstructorById("id"));
    }


    @Test
    public void whenInstructorIdExits_getInstructorById_shouldReturnInstructorDto(){
        Instructor instructor= new Instructor("id","name","surname","email", Department.ArtHistory);
        InstructorDto instructorDto= new InstructorDto("id","name","surname","email", Department.ArtHistory);
        Mockito.when(repository.findById("id")).thenReturn(Optional.of(instructor));
        Mockito.when(converter.convertToInstructorDto(instructor)).thenReturn(instructorDto);

        InstructorDto result= service.getInstructorById("id");
        assertEquals(result,instructorDto);
    }


}