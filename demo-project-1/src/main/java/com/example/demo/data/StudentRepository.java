package com.example.demo.data;

import com.example.demo.entity.Class;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select s.classcode, u.userid,  u.username, u.password, u.useremail\n" +
            "            from students s\n" +
            "            join users u on s.userid = u.userid\n" +
            "            where s.classcode =:classcode", nativeQuery = true)
    List<Student> findStudentByClasscode(int classcode);
}