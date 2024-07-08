package com.example.demo.data;

import com.example.demo.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
    @Query(value = "SELECT c.ChapterID,c.ChapterName,c.SubjectID FROM chapters c Where subjectID = :id",nativeQuery = true)
    List<Chapter> findChapterBySubject(int id);
}
