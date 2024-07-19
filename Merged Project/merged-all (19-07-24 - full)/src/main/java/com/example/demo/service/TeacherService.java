package com.example.demo.service;

import com.example.demo.data.TeacherRepository;
import com.example.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
	private final TeacherRepository teacherRepository;
	@Autowired
	public TeacherService(TeacherRepository teacherRepository) {
		
		this.teacherRepository = teacherRepository;
	}
	
	public List<Integer> getTeacherInClass(int classcode) {
		return teacherRepository.getTeacherInClass(classcode);
	}
	public Teacher getTeacherByUserId(int id) {
		return teacherRepository.findTeacherById(id);
	}
	
}
