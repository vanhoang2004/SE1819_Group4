package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Subject;
import com.example.demo.repository.SubjectRepository;
@Service
public class SubjectService{
	private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
	
	public List<Subject> getAllSubject() {
		// TODO Auto-generated method stub
		return subjectRepository.findAll();
	}


	public Subject getSubjectById(int id) {
		// TODO Auto-generated method stub
		return subjectRepository.getSubjectById(id);
	}

}
