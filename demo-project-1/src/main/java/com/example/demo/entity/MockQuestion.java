//package com.example.demo.entity;
//
//import jakarta.persistence.*;
//import jakarta.persistence.criteria.CriteriaBuilder;
//
//@Entity
//@Table(name="mocktestdetails")
//public class MockQuestion {
//
//    @ForeignKey()
//    @Column(name = "mocktestid")
//    private Integer testID;
//    @ForeignKey
//    @Column(name = "questionid")
//    private Integer questionID;
//
//    public MockQuestion() {
//    }
//
//    public MockQuestion(Integer testID, Integer questionID) {
//        this.testID = testID;
//        this.questionID = questionID;
//    }
//
//    public Integer getTestID() {
//        return testID;
//    }
//
//    public void setTestID(Integer testID) {
//        this.testID = testID;
//    }
//
//    public Integer getQuestionID() {
//        return questionID;
//    }
//
//    public void setQuestionID(Integer questionID) {
//        this.questionID = questionID;
//    }
//}
