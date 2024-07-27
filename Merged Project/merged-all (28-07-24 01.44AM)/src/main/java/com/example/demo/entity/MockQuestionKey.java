package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class MockQuestionKey implements Serializable {
    @Column(name="mocktestid")
    Integer mocktestid;

    @Column(name="questionid")
    Integer questionid;

    public MockQuestionKey() {
    }

    public Integer getMocktestid() {
        return mocktestid;
    }

    public void setMocktestid(Integer mocktestid) {
        this.mocktestid = mocktestid;
    }

    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public MockQuestionKey(Integer mocktestid, Integer questionid) {
        this.mocktestid = mocktestid;
        this.questionid = questionid;
    }
}
