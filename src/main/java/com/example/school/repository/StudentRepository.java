package com.example.school.repository;

import com.example.school.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface StudentRepository extends JpaRepository<Student,String> {
  Optional<Student> findStudentByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE Student s " +"SET s.enabled=true WHERE s.email=?1")
    int enableStudent(String email);
}
