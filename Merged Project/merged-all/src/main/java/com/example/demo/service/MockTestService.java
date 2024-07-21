package com.example.demo.service;

import com.example.demo.data.MockTestRepository;
import com.example.demo.entity.MockTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MockTestService {
	private final MockTestRepository mockTestRepository;
	
	@Autowired
	public MockTestService(MockTestRepository mockTestRepository) {
		this.mockTestRepository = mockTestRepository;
	}

	public List<MockTest> getAllMockTest() {
		// TODO Auto-generated method stub
		return mockTestRepository.findAll();
	}
	
	public List<MockTest> findMockTestBySubjectId(int id) {
		
		return mockTestRepository.findMockTestBySubjectId(id);
	}
	public MockTest getMockTestById(int id) {
		return mockTestRepository.getMockTestById(id);
	}

}
