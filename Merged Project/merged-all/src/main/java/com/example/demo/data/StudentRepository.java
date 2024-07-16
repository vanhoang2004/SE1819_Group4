package com.example.demo.data;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query("SELECT s FROM Student s WHERE s.user.userId = :userId")
    Student findStudentById(@Param("userId") int id);

    @Query("SELECT s FROM Student s WHERE s.user.username LIKE :search% OR s.user.fullname LIKE :search% OR s.user.useremail LIKE :search%")
    List<Student> search(@Param("search") String search);

    @Query("SELECT s FROM Student s JOIN s.sclass c WHERE c.classCode = :filter")
    List<Student> filterByClass(@Param("filter") int filter);
}
