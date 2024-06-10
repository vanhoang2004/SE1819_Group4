package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.MockTest;

public interface MockTestRepository extends JpaRepository<MockTest, Integer> {
	@Query(value = "SELECT * FROM MockTests WHERE SubjectID = :subjectId", nativeQuery = true)
    List<MockTest> findMockTestBySubjectId(@Param("subjectId") int subjectId);
	@Query(value = "SELECT * FROM MockTests WHERE MockTestID = :id", nativeQuery = true)
	MockTest getMockTestById(int id);
}

   

