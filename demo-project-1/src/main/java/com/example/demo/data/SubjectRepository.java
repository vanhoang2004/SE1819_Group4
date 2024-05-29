package com.example.demo.data;

import com.example.demo.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query(value = "SELECT s.SubjectID, s.Subjectname FROM subjects s "+
            "INNER JOIN managers mgr ON mgr.SubjectID = s.SubjectID "+
            "WHERE mgr.UserID = :userID",nativeQuery = true)
    Subject findSubjectByUserID(int userID);
}

