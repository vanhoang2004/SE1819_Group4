package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Subject;
import com.example.demo.repository.SubjectRepository;
@Service
public class SubjectService implements ISubjectService{
	private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
	@Override
	public List<Subject> getAllSubject() {
		// TODO Auto-generated method stub
		return subjectRepository.findAll();
	}

	@Override
	public Optional<Subject> getSubjectById(int id) {
		// TODO Auto-generated method stub
		return subjectRepository.findById(id);
	}

}
