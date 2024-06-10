package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Material;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
	@Query(value = "SELECT * FROM Materials WHERE SubjectID = :subjectId AND ChapterID = :chapterId", nativeQuery = true)
	List<Material> getAllMaterialBySubjectAndChapter(@Param("subjectId") int subjectID,@Param("chapterId") int chapterID);

}
