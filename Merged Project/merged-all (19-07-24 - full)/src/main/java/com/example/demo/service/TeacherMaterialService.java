package com.example.demo.service;

import com.example.demo.data.TeacherMaterialsRepository;
import com.example.demo.entity.TeacherMaterials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherMaterialService {
	private final TeacherMaterialsRepository teacherMaterialRepository;
	@Autowired
	public TeacherMaterialService(TeacherMaterialsRepository teacherMaterialRepository) {
		
		this.teacherMaterialRepository = teacherMaterialRepository;
	}
	public List<TeacherMaterials> getAllTeachermaterialByClassAndSubject(int classcode, int subjectid) {
		return teacherMaterialRepository.getAllTeachermaterialByClassAndSubject(classcode,subjectid);
	}
}
