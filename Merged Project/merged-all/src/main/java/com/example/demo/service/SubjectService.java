package com.example.demo.service;

import com.example.demo.data.SubjectRepository;
import com.example.demo.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
		return subjectRepository.findSubjectById(id);
	}

}
