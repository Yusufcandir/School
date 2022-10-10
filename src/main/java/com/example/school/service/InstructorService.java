package com.example.school.service;

import com.example.school.dto.InstructorDto;
import com.example.school.dto.converter.InstructorDtoConverter;
import com.example.school.exception.InstructorNotFoundException;
import com.example.school.model.Instructor;
import com.example.school.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorService {
    private final InstructorRepository repository;
    private final InstructorDtoConverter converter;

    public InstructorService(InstructorRepository repository, InstructorDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public Instructor findInstructorById(String id){
        return repository.findById(id)
                .orElseThrow(()-> new InstructorNotFoundException("Instructor could not found by id: "+ id));
    }

    public InstructorDto getInstructorById(String id){
        return converter.convertToInstructorDto(findInstructorById(id));
    }

    public List<InstructorDto> getAllInstructors(){
        return repository.findAll().stream()
                .map(converter::convertToInstructorDto)
                .collect(Collectors.toList());

    }



}
