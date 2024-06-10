package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MockTest;
import com.example.demo.entity.Student;
import com.example.demo.entity.TakenMockTest;
import com.example.demo.repository.MockTestRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TakenMocktestRepository;

@Service
public class TakenMocktestService {
	
	public final TakenMocktestRepository takenMocktestRepository;
	public final StudentRepository studentRepository;
	public final MockTestRepository mockTestRepository;
	@Autowired
	public TakenMocktestService(TakenMocktestRepository takenMocktestRepository, StudentRepository studentRepository,
			MockTestRepository mockTestRepository) {
		
		this.takenMocktestRepository = takenMocktestRepository;
		this.studentRepository = studentRepository;
		this.mockTestRepository = mockTestRepository;
	}
	
	public void saveTakenMockTest(TakenMockTest takenMockTest) {
		takenMocktestRepository.save(takenMockTest);
	}
	
	

	public void saveMockTestStartime(String username, int mocktestid, LocalDateTime starttime) {
		Student student = studentRepository.getStudentByUsername(username);
		MockTest mt = mockTestRepository.getMockTestById(mocktestid);
		TakenMockTest takenMockTest = takenMocktestRepository.findByMocktestAndStudent(mocktestid, student.getUserId());
		if(takenMockTest == null) {
			takenMockTest = new TakenMockTest();
			takenMockTest.setMocktest(mt);
			takenMockTest.setStudent(student);
		}
		takenMockTest.setStarttime(starttime);
		takenMocktestRepository.save(takenMockTest);
	}
	
	public void saveMockTestEnd(String username, int mocktestid,float score, LocalDateTime endtime) {
		Student student = studentRepository.getStudentByUsername(username);
		MockTest mt = mockTestRepository.getMockTestById(mocktestid);
		TakenMockTest takenMockTest = takenMocktestRepository.findByMocktestAndStudent(mocktestid, student.getUserId());
		if(takenMockTest != null) {
			takenMockTest.setEndtime(endtime);
			takenMockTest.setScore(score);
			takenMocktestRepository.save(takenMockTest);
		}
		else {
			throw new RuntimeException("No record found for the given mock test and student");
		}
	}
}
