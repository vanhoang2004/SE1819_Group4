package com.example.demo.data;

import com.example.demo.entity.StudentTakenPractice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentTakenPraticeRepository extends JpaRepository<StudentTakenPractice, Integer> {
	
}
