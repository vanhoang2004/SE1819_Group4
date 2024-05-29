package com.example.demo.data;

import com.example.demo.entity.Materials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Materials, Integer> {
    @Query(value = "SELECT m.MaterialID,m.SubjectID,m.ChapterID,m.Title,m.Content FROM materials m "+
            "INNER JOIN managers mgr ON mgr.SubjectID = m.SubjectID "+
            "Where mgr.UserID= :userID",nativeQuery = true)
    List<Materials> findMaterialsbySubject(int userID);

}
