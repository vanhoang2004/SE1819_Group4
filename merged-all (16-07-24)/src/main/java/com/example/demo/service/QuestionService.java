package com.example.demo.service;

import com.example.demo.data.QuestionRepository;
import com.example.demo.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;

	public List<Question> getQuestionsForUserWithLevel(int numOfQuestion, int subjectId, int chapterId, int levelid) {
		Pageable pageable = PageRequest.of(0, numOfQuestion);
		return questionRepository.findBySubjectIdAndChapterIdWithLevel(subjectId, chapterId, levelid, pageable);
	}

	public List<Question> getQuestionByMocktest(int mocktestId) {
		return questionRepository.getQuestionByMocktest(mocktestId);
	}

	public List<Question> getAllQuestions() {
		return questionRepository.findAll();
	}

	public List<Question> getQuestionByTeacherPractice(int teacherPracticeId) {
		return questionRepository.getQuestionByTeacherPractice(teacherPracticeId);
	}
}
