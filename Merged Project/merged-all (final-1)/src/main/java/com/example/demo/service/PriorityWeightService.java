package com.example.demo.service;

import com.example.demo.data.PriorityWeightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityWeightService {
	private final PriorityWeightRepository priorityWeightRepository;
	private final StudentService studentService;
	private final QuestionService questionService;
	@Autowired
	public PriorityWeightService(PriorityWeightRepository priorityWeightRepository, StudentService studentService, QuestionService questionService) {

		this.priorityWeightRepository = priorityWeightRepository;
		this.studentService = studentService;
		this.questionService=questionService;
	}
	@Transactional
	public void updatePriorityWeight(int userid, List<Integer> questionid) {

		for(int i : questionid) {

			priorityWeightRepository.insertPriority(userid, i);
		}
	}

	@Scheduled(cron = "0 0 1 * * *")
	@Transactional
	public void checkAndRemovePriorityWeights() {
		// Lấy tất cả các UserID từ bảng PriorityWeight
		List<Integer> userIds = priorityWeightRepository.findDistinctUserIds();

		for (Integer userId : userIds) {
			// Kiểm tra từng LevelID, ChapterID và SubjectID cho mỗi UserID
			List<Object[]> results = priorityWeightRepository.countQuestionsForUser(userId);

			for (Object[] result : results) {
				int levelId = (int) result[0];
				int chapterId = (int) result[1];
				int subjectId = (int) result[2];
				int userQuestionsCount = (int) result[3];
				int totalQuestionsCount = questionService.countQuestionsByLevelAndChapterAndSubject(levelId, chapterId, subjectId);
				// So sánh số câu hỏi của user với tổng số câu hỏi
				if (userQuestionsCount == totalQuestionsCount) {
					// Xóa tất cả các bản ghi của user trong PriorityWeight
					priorityWeightRepository.deleteByUserId(userId);
				}
			}
		}
	}



//	public void savePriority(List<PriorityWeight> priority) {
//		priorityWeightRepository.save(priority);
//	}
}
