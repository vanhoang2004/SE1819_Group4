package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	@Query(value = "SELECT * FROM Questions WHERE SubjectID = :subjectid AND ChapterID= :chapterid", nativeQuery = true)
	List<Question> findBySubjectIdAndChapterId(int subjectid, int chapterid, Pageable pageable);
	@Query(value = "SELECT \r\n"
			+ "    q.QuestionID , \r\n"
			+ "    q.QuestionTitle , \r\n"
			+ "    q.Image , \r\n"
			+ "    q.Option1, \r\n"
			+ "    q.Option2 , \r\n"
			+ "    q.Option3, \r\n"
			+ "    q.Option4, \r\n"
			+ "    q.Answer, \r\n"
			+ "    q.SubjectID, \r\n"
			+ "    q.ChapterID, \r\n"
			+ "    q.LevelID, \r\n"
			+ "    q.status \r\n"
	
			+ "FROM \r\n"
			+ "    demo1.questions q \r\n"
			+ "JOIN \r\n"
			+ "    demo1.mocktestdetails md \r\n"
			+ "ON \r\n"
			+ "    q.QuestionID = md.QuestionID \r\n"
			+ "JOIN \r\n"
			+ "    demo1.mocktests m \r\n"
			+ "ON \r\n"
			+ "    m.MockTestID = md.MockTestID \r\n"
			+ "WHERE \r\n"
			+ "    m.MockTestID =:mocktestid", nativeQuery = true)
	List<Question> getQuestionByMocktest(int mocktestid);
	@Query(value = "SELECT q.QuestionID, q.QuestionTitle, q.Image, q.Option1, q.Option2, q.Option3, q.Option4, q.Answer, q.SubjectID, q.ChapterID, q.LevelID, q.status\r\n"
			+ "FROM teacherpractice AS t\r\n"
			+ "JOIN teacherpracticedetails AS td ON t.TeacherPracticeID = td.TeacherPracticeID\r\n"
			+ "JOIN questions AS q ON td.QuestionID = q.QuestionID\r\n"
			+ "WHERE t.TeacherPracticeID = :id", nativeQuery = true)
	List<Question> getQuestionByTeacherPractice(int id);
}
