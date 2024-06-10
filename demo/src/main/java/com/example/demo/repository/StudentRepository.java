package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	@Query(value="SELECT s.* FROM Students s JOIN Users u ON u.UserID = s.UserID WHERE u.Username = :username", nativeQuery = true) 
	Student getStudentByUsername(@Param("username") String username);

}
