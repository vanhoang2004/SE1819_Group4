package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TeacherPractice;
import com.example.demo.repository.TeacherPracticeRepository;
@Service
public class TeacherPracticeService {
	private final TeacherPracticeRepository teacherPracticeRepository;
	@Autowired
	public TeacherPracticeService(TeacherPracticeRepository teacherPracticeRepository) {
		this.teacherPracticeRepository = teacherPracticeRepository;
	}
	public List<TeacherPractice> getTeacherPracticeByClassAndSubject(int classcode, int subjectid) {
		return teacherPracticeRepository.getTeacherPracticeByClassAndSubject(classcode, subjectid);
	}
	
}
