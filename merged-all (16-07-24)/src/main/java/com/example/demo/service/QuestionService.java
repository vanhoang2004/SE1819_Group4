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

	public int countQuestionsByLevelAndChapterAndSubject(int levelId, int chapterId, int subjectId) {
		// TODO Auto-generated method stub
		return questionRepository.countQuestionsByLevelAndChapterAndSubject(levelId, chapterId, subjectId);
	}
	public List<Question> getQuestionsForUserWithLevelAndWeight1(int numOfQuestion, int userid, int subjectId, int chapterId, int levelid) {
		Pageable pageable = PageRequest.of(0, numOfQuestion);
		return questionRepository.findBySubjectIdAndChapterIdWithLevelAndWeight1(userid,subjectId, chapterId, levelid);
	}
	public List<Question> getQuestionsForUserWithLevelAndWeight0(int numOfQuestion, int userid, int subjectId, int chapterId, int levelid) {
		Pageable pageable = PageRequest.of(0, numOfQuestion);
		return questionRepository.findBySubjectIdAndChapterIdWithLevelAndWeight0(userid,subjectId, chapterId, levelid);
	}
}
