package com.example.school.controller;

import com.example.school.dto.StudentDto;
import com.example.school.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable String studentId){
     return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }


}
