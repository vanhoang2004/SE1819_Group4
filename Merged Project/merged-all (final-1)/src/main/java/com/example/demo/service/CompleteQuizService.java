package com.example.demo.service;

import com.example.demo.data.ChapterRepository;
import com.example.demo.data.CompleteQuizRepository;
import com.example.demo.data.StudentRepository;
import com.example.demo.entity.Chapter;
import com.example.demo.entity.CompleteQuiz;
import com.example.demo.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompleteQuizService {
	private final CompleteQuizRepository completeQuizRepository;
	private final StudentRepository studentRepository;
	private final ChapterRepository chapterRepository;
	@Autowired
	public CompleteQuizService(CompleteQuizRepository completeQuizRepository, StudentRepository studentRepository,
                               ChapterRepository chapterRepository) {
		
		this.completeQuizRepository = completeQuizRepository;
		this.studentRepository = studentRepository;
		this.chapterRepository = chapterRepository;
	}

	@Transactional
	public void saveCompleteQuiz(CompleteQuiz completeQuiz) {
		Student student = studentRepository.findStudentById(completeQuiz.getStudent().getUserId());
		Chapter chapter = chapterRepository.getChapterByChapterId(completeQuiz.getChapter().getId());
		completeQuiz.setChapter(chapter);
		completeQuiz.setStudent(student);
		completeQuizRepository.save(completeQuiz);
	}
	
	 public List<CompleteQuiz> getCompleteQuizByChapterIdAndUserid( int chapterid, int userid) {
		 return completeQuizRepository.getCompleteQuizByChapterIdAndUserid(chapterid, userid);
	 }
	 
	 public List<CompleteQuiz> getCompleteQuizBySubjectIdAndUserid(int subjectid, int userid) {
		 return completeQuizRepository.getCompleteQuizBySubjectIdAndUserid(subjectid, userid);
	 }
	 
	 public int getCountQuizByUserAndChapter(int chapterid, int userid) {
		 return completeQuizRepository.getCountQuizByUserAndChapter(chapterid, userid);
	 }

	
}
