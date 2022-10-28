//package com.example.school.service;
//
//import com.example.school.dto.StudentDto;
//import com.example.school.dto.converter.StudentDtoConverter;
//
//import com.example.school.exception.StudentNotFoundException;
//import com.example.school.model.Student;
//import com.example.school.repository.StudentRepository;
//import org.junit.jupiter.api.BeforeEach;
//
//import org.junit.jupiter.api.Test;
//
//import org.mockito.Mockito;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;
//import static org.mockito.Mockito.mock;
//
//class StudentServiceTest  {
//
//
//    private StudentService service;
//
//    private StudentRepository repository;
//
//    private StudentDtoConverter converter;
//
//
//    @BeforeEach
//    public void setUp(){
//       repository=mock(StudentRepository.class);
//       converter=mock(StudentDtoConverter.class);
//       service= new StudentService(repository,converter);
//    }
//
//    @Test
//    public void whenStudentIdExits_findStudentById_shouldReturnStudent(){
//        Student student= new Student("id","name","surname","email", Set.of());
//
//        Mockito.when(repository.findById("id")).thenReturn(Optional.of(student));
//
//        Student result= service.findStudentById("id");
//        assertEquals(result, student);
//    }
//
//    @Test
//    public void whenStudentIdDoesNotExits_findStudentById_shouldThrowStudentNotFoundException(){
//        Mockito.when(repository.findById("id")).thenReturn(Optional.empty());
//        assertThrows( StudentNotFoundException.class,()->service.findStudentById("id"));
//    }
//
//    @Test
//    public void whenStudentIdExits_getStudentById_shouldReturnStudentDto(){
//        Student student= new Student("id","name","surname","email", Set.of());
//        StudentDto studentDto= new StudentDto("id","name","surname","email", Set.of());
//
//        Mockito.when(repository.findById("id")).thenReturn(Optional.of(student));
//        Mockito.when(converter.convert(student)).thenReturn(studentDto);
//
//        StudentDto result= service.getStudentById("id");
//        assertEquals(result,studentDto);
//    }
//
//    @Test
//    public void whenStudentIdDoesNotExits_getStudentById_shouldThrowStudentNotfoundException(){
//
//        Mockito.when(repository.findById("id")).thenReturn(Optional.empty());
//        Mockito.verifyNoInteractions(converter);
//        assertThrows(StudentNotFoundException.class,()->service.getStudentById("id"));
//
//    }
//
//    @Test
//    public void whenStudentsAreFound_getAllStudents_shouldReturnListOfStudentDto(){
//        StudentDto studentDto= new StudentDto("id","name","surname","email",Set.of());
//        Student student= new Student("id","name","surname","email", Set.of());
//
//        Mockito.when(repository.findAll()).thenReturn(List.of(student));
//        Mockito.when(converter.convert(student)).thenReturn(studentDto);
//
//        List<StudentDto> result=service.getAllStudents();
//        assertEquals(result,List.of(studentDto));
//    }
//
//
//
//
//}