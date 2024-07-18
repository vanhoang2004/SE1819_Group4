package com.example.demo.data;

import com.example.demo.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    @Query(value = "SELECT m.MaterialID,m.SubjectID,m.ChapterID,m.Title,m.Content FROM materials m "+
            "INNER JOIN managers mgr ON mgr.SubjectID = m.SubjectID "+
            "Where mgr.UserID= :userID",nativeQuery = true)
    List<Material> findMaterialsByManager(int userID);

    @Query(value ="SELECT * FROM materials Where MaterialID = :id",nativeQuery = true)
    Material findMaterialbyID(int id);

    @Query(value = "SELECT m.MaterialID,m.SubjectID,m.ChapterID,m.Title,m.Content FROM materials m "+
            "INNER JOIN managers mgr ON mgr.SubjectID = m.SubjectID "+
            "Where mgr.UserID= :userID and m.Title like %:keyword%",nativeQuery = true)
    List<Material> searchMaterial(int userID, @Param("keyword") String keyword);

    @Query(value = "SELECT * FROM Materials WHERE SubjectID = :subjectId AND ChapterID = :chapterId", nativeQuery = true)
    List<Material> getAllMaterialBySubjectAndChapter(@Param("subjectId") int subjectID,@Param("chapterId") int chapterID);
}
