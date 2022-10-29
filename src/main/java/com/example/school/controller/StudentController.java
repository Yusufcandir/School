package com.example.school.controller;

import com.example.school.dto.StudentDto;
import com.example.school.model.Student;
import com.example.school.service.StudentService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public void save(@RequestBody Student student) {
         studentService.save(student);
    }

    @PutMapping("/{studentId}")
    public void update(@RequestBody Student student,@PathVariable String studentId){
        studentService.update(student,studentId);
    }

    @DeleteMapping("/{studentId}")
    public void delete(@PathVariable String studentId){
        studentService.delete(studentId);
    }


}
