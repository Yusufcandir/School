package com.example.school.repository;

import com.example.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
    Optional<Student> findByEmail(String email);


    @Transactional
    @Modifying
    @Query("UPDATE Student a SET a.enabled=true WHERE a.email=?1")
    int enableAppUser(String email);
}
