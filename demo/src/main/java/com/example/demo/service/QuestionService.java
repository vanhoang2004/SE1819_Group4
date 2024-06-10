package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Question;
import com.example.demo.repository.QuestionRepository;

@Service
public class QuestionService {
	 @Autowired
	 private QuestionRepository questionRepository;
	 public List<Question> getQuestionsForUser(int numOfQuestion, int subjectId, int chapterId) {
	        Pageable pageable = PageRequest.of(0, numOfQuestion);
	        return questionRepository.findBySubjectIdAndChapterId(subjectId, chapterId, pageable);
	    }
	 public List<Question> getQuestionByMocktest(int mocktestid) {
		 return questionRepository.getQuestionByMocktest(mocktestid);
	 }
	 public List<Question> getAllQuestion() {
		 return questionRepository.findAll();
	 }
	 public List<Question> getQuestionByTeacherPractice(int id) {
		 return questionRepository.getQuestionByTeacherPractice(id);
	 }
}
