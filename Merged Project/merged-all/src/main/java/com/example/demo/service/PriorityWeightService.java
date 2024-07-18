package com.example.demo.service;

import com.example.demo.data.PriorityWeightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityWeightService {
	private final PriorityWeightRepository priorityWeightRepository;
	@Autowired
	public PriorityWeightService(PriorityWeightRepository priorityWeightRepository) {
		
		this.priorityWeightRepository = priorityWeightRepository;
	}
	@Transactional
	public void updatePriorityWeight(int userid, List<Integer> questionid) {
		priorityWeightRepository.updatePriorityWeight(userid, questionid);
	}
}
