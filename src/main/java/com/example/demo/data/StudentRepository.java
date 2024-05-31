package com.example.demo.data;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query("SELECT s FROM Student s WHERE s.user.userId = :userId")
    Student findStudentById(@Param("userId") int id);
}
