package com.example.demo.data;

import com.example.demo.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
    @Query(value = "SELECT c.ChapterID,c.ChapterName,c.SubjectID FROM chapters c Where subjectID = :subjectid",nativeQuery = true)
    List<Chapter> findChapterBySubject(int subjectid);

    @Query(value = "SELECT * FROM Chapters WHERE SubjectID = :subjectId", nativeQuery = true)
    List<Chapter> findChapterBySubjectId(@Param("subjectId") int subjectId);

    @Query(value = "SELECT * FROM Chapters WHERE ChapterID = :chapterId", nativeQuery = true)
    Optional<Chapter> getChapterByChapterId(@Param("chapterId") int chapterId);
}
