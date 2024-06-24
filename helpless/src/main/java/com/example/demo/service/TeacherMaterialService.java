package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.TeacherMaterials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TeacherMaterials;
import com.example.demo.data.TeacherMaterialsRepository;
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
