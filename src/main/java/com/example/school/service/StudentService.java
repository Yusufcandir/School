package com.example.school.service;

import com.example.school.config.PasswordEncoder;
import com.example.school.dto.StudentDto;
import com.example.school.dto.converter.StudentDtoConverter;
import com.example.school.exception.InstructorNotFoundException;
import com.example.school.exception.StudentNotFoundException;
import com.example.school.model.Student;
import com.example.school.model.token.ConfirmationToken;
import com.example.school.model.token.ConfirmationTokenService;
import com.example.school.repository.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class StudentService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final StudentDtoConverter converter;

    private final ConfirmationTokenService tokenService;

    private final PasswordEncoder encoder;

    public StudentService(StudentRepository studentRepository, StudentDtoConverter converter,
                          ConfirmationTokenService tokenService, PasswordEncoder encoder) {
        this.studentRepository = studentRepository;
        this.converter = converter;
        this.tokenService = tokenService;
        this.encoder = encoder;
    }


    public Student findStudentById(String id){
        return studentRepository.findById(id).orElseThrow(
                () ->   new StudentNotFoundException("Student could not found by id: " + id));
    }

    public StudentDto getStudentById(String id){
      return converter.convert(findStudentById(id));
    }

    public List<StudentDto> getAllStudents(){
        return studentRepository.findAll().stream()
                .map(converter::convert).collect(Collectors.toList());
    }

    public void save(Student student){
        studentRepository.save(student);
    }

    public void update(Student student,String id){
        Optional<Student> exits= studentRepository.findById(id);
        if (exits.isPresent()){
            studentRepository.save(student);
        }
        else {
            throw new StudentNotFoundException("There is no such a student with id: "+ id);
        }
    }

    public void delete(String id){
        boolean exits = studentRepository.existsById(id);
        if (!exits){
            throw new InstructorNotFoundException("There is no such a student with id: "+ id);
        }
        studentRepository.deleteById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return studentRepository.findStudentByEmail(email)
                .orElseThrow(()-> new StudentNotFoundException(String.format("Student with email % not found "+ email)));
    }

    public String signUpStudent(Student student){
    boolean exits= studentRepository.findStudentByEmail(student.getEmail()).isPresent();

    if (exits){

        Student beforeSignUp= studentRepository.findStudentByEmail(student.getEmail()).get();
        Boolean isEnabled=beforeSignUp.getEnabled();

        if (!isEnabled){
            String token = UUID.randomUUID().toString();
            saveConfirmationToken(beforeSignUp,token);

            return token;
        }

        throw new IllegalStateException(String.format("Student with email %s is already exists!", student.getEmail()));

    }

    String encodedPassword= encoder.bCryptPasswordEncoder().encode(student.getPassword());
    student.setPassword(encodedPassword);

    studentRepository.save(student);

    String token= UUID.randomUUID().toString();

    saveConfirmationToken(student,token);

    return token;

    }

    protected void saveConfirmationToken(Student student, String token){
        ConfirmationToken confirmationToken= new ConfirmationToken(token, LocalDateTime.now()
        ,LocalDateTime.now().plusMinutes(15),student);

        tokenService.saveConfirmationToken(confirmationToken);

    }

    public int enableAppUser(String email){
        return studentRepository.enableStudent(email);
    }


}
