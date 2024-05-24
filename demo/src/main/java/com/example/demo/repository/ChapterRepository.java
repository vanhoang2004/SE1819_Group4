package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Chapter;


public interface ChapterRepository extends JpaRepository<Chapter, Integer>{
	@Query(value = "SELECT * FROM Chapters WHERE SubjectID = :subjectId", nativeQuery = true)
	List<Chapter> findChapterBySubjectId(@Param("subjectId") int subjectId);

}
