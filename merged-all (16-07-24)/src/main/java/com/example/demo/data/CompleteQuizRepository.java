package com.example.demo.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.CompleteQuiz;


public interface CompleteQuizRepository extends JpaRepository<CompleteQuiz, Integer>{
	@Query(value = "SELECT * FROM completeQuiz WHERE userid = :userid AND chapterid = :chapterid", nativeQuery = true)
 List<CompleteQuiz> getCompleteQuizByChapterIdAndUserid(@Param("chapterid") int chapterid,@Param("userid") int userid);
	@Query(value = "SELECT * FROM completeQuiz WHERE userid = :userid AND chapterid in (select chapterid from chapters where subjectid=:subjectid)", nativeQuery = true)
	 List<CompleteQuiz> getCompleteQuizBySubjectIdAndUserid(@Param("subjectid") int subjectid,@Param("userid") int userid);
	@Query(value = "SELECT COUNT(*) FROM completeQuiz WHERE userid = :userid AND chapterid = :chapterid", nativeQuery = true)
	int getCountQuizByUserAndChapter(@Param("chapterid") int chapterid,@Param("userid") int userid);
}
