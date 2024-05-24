package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MockTest;
import com.example.demo.repository.MockTestRepository;
@Service
public class MockTestService implements IMockTestService{
	private final MockTestRepository mockTestRepository;
	
	@Autowired
	public MockTestService(MockTestRepository mockTestRepository) {
		this.mockTestRepository = mockTestRepository;
	}


	@Override
	public List<MockTest> getAllMockTest() {
		// TODO Auto-generated method stub
		return mockTestRepository.findAll();
	}


	@Override
	public List<MockTest> findMockTestBySubjectId(int id) {
		
		return mockTestRepository.findMockTestBySubjectId(id);
	}

}
