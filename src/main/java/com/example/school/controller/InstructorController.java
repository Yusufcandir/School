package com.example.school.controller;

import com.example.school.dto.InstructorDto;
import com.example.school.model.Instructor;
import com.example.school.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void save(@RequestBody Instructor instructor){
        service.save(instructor);
    }

    @PutMapping("/{instructorId}")
    public void update(@RequestBody Instructor instructor, @PathVariable String instructorId ){
        service.update(instructor,instructorId);

    }

    @DeleteMapping("/{instructorId}")
    public void delete(@PathVariable String instructorId){
        service.delete(instructorId);
    }

    
}
