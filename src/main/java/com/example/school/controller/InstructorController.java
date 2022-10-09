package com.example.school.controller;

import com.example.school.dto.InstructorDto;
import com.example.school.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/instructor")
public class InstructorController {
    private final InstructorService service;

    public InstructorController(InstructorService service) {
        this.service = service;
    }
    @GetMapping("/{instructorId}")
    public ResponseEntity<InstructorDto> getInstructorById(@PathVariable String instructorId){
        return ResponseEntity.ok(service.getInstructorById(instructorId));
    }

    @GetMapping
    public ResponseEntity<List<InstructorDto>> getAllInstructors(){
        return ResponseEntity.ok(service.getAllInstructors());
    }
}
