package com.example.demo.data;

import com.example.demo.entity.CompleteQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CompleteQuizRepository extends JpaRepository<CompleteQuiz, Integer>{
	@Query(value = "SELECT * FROM completeQuiz WHERE userid = :userid AND chapterid = :chapterid", nativeQuery = true)
 List<CompleteQuiz> getCompleteQuizByChapterIdAndUserid(@Param("chapterid") int chapterid,@Param("userid") int userid);
	@Query(value = "SELECT * FROM completeQuiz WHERE userid = :userid AND chapterid in (select chapterid from chapters where subjectid=:subjectid)", nativeQuery = true)
	 List<CompleteQuiz> getCompleteQuizBySubjectIdAndUserid(@Param("subjectid") int subjectid,@Param("userid") int userid);
	@Query(value = "SELECT COUNT(*) FROM completeQuiz WHERE userid = :userid AND chapterid = :chapterid", nativeQuery = true)
	int getCountQuizByUserAndChapter(@Param("chapterid") int chapterid,@Param("userid") int userid);
}
