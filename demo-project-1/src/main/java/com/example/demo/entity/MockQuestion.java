package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name="mocktestdetails")
public class MockQuestion {
    @Id
    @Column(name="mocktestid")
    private  Integer mocktestID;

@Column(name="questionid")
    private Integer questionID;

    public MockQuestion() {
    }

    public Integer getMocktestID() {
        return mocktestID;
    }

    public void setMocktestID(Integer mocktestID) {
        this.mocktestID = mocktestID;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public MockQuestion(Integer mocktestID, Integer questionID) {
        this.mocktestID = mocktestID;
        this.questionID = questionID;
    }
}
