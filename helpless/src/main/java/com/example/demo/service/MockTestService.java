package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Mocktest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Mocktest;
import com.example.demo.data.MocktestRepository;
@Service
public class MockTestService {
	private final MocktestRepository mockTestRepository;
	
	@Autowired
	public MockTestService(MocktestRepository mockTestRepository) {
		this.mockTestRepository = mockTestRepository;
	}



	public List<Mocktest> getAllMockTest() {
		// TODO Auto-generated method stub
		return mockTestRepository.findAll();
	}


	
	public List<Mocktest> findMockTestBySubjectId(int id) {
		
		return mockTestRepository.findMockTestBySubjectId(id);
	}
	public Mocktest getMockTestById(int id) {
		return mockTestRepository.getMockTestById(id);
	}

}
