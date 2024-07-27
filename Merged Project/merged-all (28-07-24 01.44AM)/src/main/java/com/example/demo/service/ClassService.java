package com.example.demo.service;

import com.example.demo.data.ClassRepository;
import com.example.demo.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ClassService {
	
	private final ClassRepository classRepository;
	@Autowired
	public ClassService(ClassRepository classRepository) {
		
		this.classRepository = classRepository;
	}
	
	public Class getClassByClasscode (int classcode) {
		return classRepository.findClassById(classcode);
	}
	
	
	
}
