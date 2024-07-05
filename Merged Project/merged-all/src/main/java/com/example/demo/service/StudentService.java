package com.example.demo.service;

import com.example.demo.data.StudentRepository;
import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
	public Student getStudentByUserID(int id) {
		return studentRepository.findStudentById(id);
	}

	public List<Student> getStudentInClass(int classcode) {
		return studentRepository.findStudentByClasscode(classcode);
	}
}
