package com.example.demo.service;

import com.example.demo.data.TeacherPracticeRepository;
import com.example.demo.entity.TeacherPractice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
	public TeacherPractice getTeacherPracticeById(int id) {
		return teacherPracticeRepository.getTeacherPracticeById(id);
	}
}
