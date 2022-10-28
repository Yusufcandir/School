package com.example.school.service;

import com.example.school.config.PasswordEncoder;
import com.example.school.dto.StudentDto;
import com.example.school.dto.converter.StudentDtoConverter;
import com.example.school.exception.InstructorNotFoundException;
import com.example.school.exception.StudentNotFoundException;
import com.example.school.model.ConfirmationToken;
import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final PasswordEncoder encoder;




    private final ConfirmationTokenService confirmationTokenService;

    public StudentService(StudentRepository studentRepository, StudentDtoConverter converter,
                          PasswordEncoder encoder, ConfirmationTokenService confirmationTokenService) {
        this.studentRepository = studentRepository;
        this.converter = converter;
        this.encoder = encoder;
        this.confirmationTokenService= confirmationTokenService;

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
        return studentRepository.findByEmail(email).
                orElseThrow(()-> new StudentNotFoundException(String.format("User with email %s not found",email)));
    }


    public String singUp(Student student){
        boolean exists= studentRepository.findByEmail(student.getEmail()).isPresent();

        if (exists){
            Student student1= studentRepository.findByEmail(student.getEmail()).get();
            Boolean isEnabled= student1.getEnabled();

            if (!isEnabled){
                String token= UUID.randomUUID().toString();
                saveConfirmationToken(student1,token);
                return token;
            }

            throw new IllegalStateException(String.format("User with email %s already exists!", student.getEmail()));
        }


        String encodedPassword= encoder.bCryptPasswordEncoder().encode(student.getPassword());
        student.setPassword(encodedPassword);

        // after encoding the password saving student
        studentRepository.save(student);

        // creating a token
        String token = UUID.randomUUID().toString();

        // getting the confirmation token and save it
        saveConfirmationToken(student,token);

        return token;

    }

    public void saveConfirmationToken(Student student,String token){
        ConfirmationToken confirmationToken= new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),student);
        confirmationTokenService.save(confirmationToken);
    }

    public int enableStudent(String email){
        return studentRepository.enableAppUser(email);
    }


}
