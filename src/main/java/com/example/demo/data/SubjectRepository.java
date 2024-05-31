package com.example.demo.data;

import com.example.demo.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    @Query("SELECT s FROM Subject s WHERE s.subjectId = :subjectId")
    Subject findSubjectById(@Param("subjectId") int id);
}
