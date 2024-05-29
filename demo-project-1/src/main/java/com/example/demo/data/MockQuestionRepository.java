package com.example.demo.data;

import com.example.demo.entity.MockQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MockQuestionRepository extends JpaRepository<MockQuestion, Integer> {
    @Query(value = "SELECT MockTestID, QuestionID FROM mocktestdetails WHERE MockTestID = :id",nativeQuery = true)
    List<MockQuestion> mockDetails(int id);
}
