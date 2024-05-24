package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.MockTest;

public interface IMockTestService {
	List<MockTest> getAllMockTest();
	List<MockTest> findMockTestBySubjectId(int id);
}
