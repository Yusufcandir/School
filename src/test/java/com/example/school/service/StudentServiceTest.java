package com.example.school.service;

import com.example.school.config.PasswordEncoder;
import com.example.school.dto.StudentDto;
import com.example.school.dto.converter.StudentDtoConverter;

import com.example.school.exception.StudentNotFoundException;
import com.example.school.model.Student;
import com.example.school.model.UserRole;
import com.example.school.model.token.ConfirmationTokenService;
import com.example.school.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import static org.mockito.Mockito.mock;

class StudentServiceTest {


    private StudentService service;

    private StudentRepository repository;

    private StudentDtoConverter converter;

    private ConfirmationTokenService tokenService;

    private PasswordEncoder encoder;

    @BeforeEach
    public void setUp() {
        repository = mock(StudentRepository.class);
        converter = mock(StudentDtoConverter.class);
        tokenService = mock(ConfirmationTokenService.class);
        encoder = mock(PasswordEncoder.class);
        service = new StudentService(repository, converter, tokenService, encoder);
    }

    @Test
    public void whenStudentIdExits_findStudentById_shouldReturnStudent() {
        Student student =
                new Student("id", "name", "surname", "email", "password", UserRole.USER, Set.of());

        Mockito.when(repository.findById("id")).thenReturn(Optional.of(student));

        Student result = service.findStudentById("id");
        assertEquals(result, student);
    }

    @Test
    public void whenStudentIdDoesNotExits_findStudentById_shouldThrowStudentNotFoundException() {
        Mockito.when(repository.findById("id")).thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class, () -> service.findStudentById("id"));
    }

    @Test
    public void whenStudentIdExits_getStudentById_shouldReturnStudentDto() {
        Student student = new Student("id", "name", "surname", "email", "password", UserRole.USER, Set.of());
        StudentDto studentDto =
                new StudentDto("id", "name", "surname", "email", "password", UserRole.USER, Set.of());

        Mockito.when(repository.findById("id")).thenReturn(Optional.of(student));
        Mockito.when(converter.convert(student)).thenReturn(studentDto);

        StudentDto result = service.getStudentById("id");
        assertEquals(result, studentDto);
    }

    @Test
    public void whenStudentIdDoesNotExits_getStudentById_shouldThrowStudentNotfoundException() {

        Mockito.when(repository.findById("id")).thenReturn(Optional.empty());
        Mockito.verifyNoInteractions(converter);
        assertThrows(StudentNotFoundException.class, () -> service.getStudentById("id"));

    }

    @Test
    public void whenStudentsAreFound_getAllStudents_shouldReturnListOfStudentDto() {
        StudentDto studentDto =
                new StudentDto("id", "name", "surname", "email", "password", UserRole.USER, Set.of());
        Student student = new Student("id", "name", "surname", "email", "password", UserRole.USER, Set.of());

        Mockito.when(repository.findAll()).thenReturn(List.of(student));
        Mockito.when(converter.convert(student)).thenReturn(studentDto);

        List<StudentDto> result = service.getAllStudents();
        assertEquals(result, List.of(studentDto));
    }

    @Test
    public void whenStudentEmailExits_loadUserByUsername_shouldReturnStudent() {
        Student student =
                new Student("id", "name", "surname", "email", "password", UserRole.USER, Set.of());

        Mockito.when(repository.findStudentByEmail("email")).thenReturn(Optional.of(student));

        UserDetails result = service.loadUserByUsername("email");
        assertEquals(result, student);
    }

    @Test
    public void whenStudentEmailExits_loadUserByUsername_shouldThrowIllegalArgumentException() {
        Mockito.when(repository.findStudentByEmail("email")).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> service.loadUserByUsername("email"));
    }

    //  spring-security has BCryptPasswordEncoderTests.java. To allow testing your use of it
//    @Test
//    public void whenStudentEmailDoesNotExits_signUpStudent_shouldReturnToken() {
//        Student student =
//                new Student("id", "name", "surname", "email", "password", UserRole.USER, Set.of());
//
//
//        Mockito.when(repository.findStudentByEmail("email")).thenReturn(Optional.empty());
//        Mockito.when(encoder.bCryptPasswordEncoder().encode("password")).thenReturn(student.getPassword());
//
//        Mockito.verifyNoInteractions(tokenService);
//
//
//        String result = service.signUpStudent(student);
//        assertEquals(result,student);
//
//
//
//    }

    @Test
    public void enableAppUser_shouldReturnInteger(){
        Mockito.when(repository.enableStudent("email")).thenReturn(1);
        int result = service.enableAppUser("email");
        assertEquals(result, 1);
    }
}