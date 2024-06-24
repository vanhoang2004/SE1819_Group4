package com.example.demo.service;

import java.time.LocalDateTime;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Mocktest;
import com.example.demo.entity.Student;
import com.example.demo.entity.TakenMockTest;
import com.example.demo.data.MocktestRepository;
import com.example.demo.data.StudentRepository;
import com.example.demo.data.TakenMocktestRepository;

@Service
public class TakenMocktestService {
	
	public final TakenMocktestRepository takenMocktestRepository;
	public final StudentRepository studentRepository;
	public final MocktestRepository mockTestRepository;
	@Autowired
	public TakenMocktestService(TakenMocktestRepository takenMocktestRepository, StudentRepository studentRepository,
			MocktestRepository mockTestRepository) {
		
		this.takenMocktestRepository = takenMocktestRepository;
		this.studentRepository = studentRepository;
		this.mockTestRepository = mockTestRepository;
	}
	
	public void saveTakenMockTest(TakenMockTest takenMockTest) {
		takenMocktestRepository.save(takenMockTest);
	}
	
	public void generateExcel(HttpServletResponse response) {

	}


}
