package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Subject;

public interface ISubjectService {
	List<Subject> getAllSubject();
	Optional<Subject> getSubjectById(int id);
	
}
