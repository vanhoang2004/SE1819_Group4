package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TeacherMaterial;
import com.example.demo.repository.TeacherMaterialRepository;
@Service
public class TeacherMaterialService {
	private final TeacherMaterialRepository teacherMaterialRepository;
	@Autowired
	public TeacherMaterialService(TeacherMaterialRepository teacherMaterialRepository) {
		
		this.teacherMaterialRepository = teacherMaterialRepository;
	}
	public List<TeacherMaterial> getAllTeachermaterialByClassAndSubject(int classcode, int subjectid) {
		return teacherMaterialRepository.getAllTeachermaterialByClassAndSubject(classcode,subjectid);
	}
}
