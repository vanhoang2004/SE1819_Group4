package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.TeacherPractice;

public interface TeacherPracticeRepository extends JpaRepository<TeacherPractice, Integer> {
	@Query(value="SELECT * FROM TeacherPractice WHERE ClassCode = :classcode AND SubjectID =:subjectid", nativeQuery = true)
	List<TeacherPractice> getTeacherPracticeByClassAndSubject(int classcode, int subjectid);


}
