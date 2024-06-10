package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
@Service
public class StudentService {
	private final StudentRepository studentRepository;
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		
		this.studentRepository = studentRepository;
	}

	public Student getStudentByUsername(String username) {
		return studentRepository.getStudentByUsername(username);
	}
}
