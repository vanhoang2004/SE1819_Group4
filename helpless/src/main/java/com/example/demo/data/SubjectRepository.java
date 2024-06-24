package com.example.demo.data;

import com.example.demo.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query(value="SELECT * from subjects where subjectid = :id", nativeQuery = true)
    Subject getSubjectById(int id);
}
