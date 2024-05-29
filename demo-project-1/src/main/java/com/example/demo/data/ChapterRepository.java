package com.example.demo.data;

import com.example.demo.entity.Chapters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapters, Integer> {
    @Query(value = "SELECT c.ChapterID,c.ChapterName,c.SubjectID FROM chapters c Where subjectID = :id",nativeQuery = true)
    List<Chapters> findChapterBySubject(int id);
}
