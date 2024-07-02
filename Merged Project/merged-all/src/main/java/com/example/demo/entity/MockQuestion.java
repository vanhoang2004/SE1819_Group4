package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="mocktestdetails")
public class MockQuestion {


    @EmbeddedId
    private MockQuestionKey id;

    @ManyToOne
    @MapsId("mocktestid")
    @JoinColumn(name="mocktestid")
    private MockTest mockTest;


    @ManyToOne
    @MapsId("questionid")
    @JoinColumn(name="questionid")
    private  Question question;

    public MockQuestion() {
    }


    public MockQuestion(MockTest mockTest, Question question) {
        this.mockTest = mockTest;
        this.question = question;
    }

    public MockQuestionKey getId() {
        return id;
    }


    public void setId(MockQuestionKey id) {
        this.id = id;
    }

    public MockTest getMockTest() {
        return mockTest;
    }

    public void setMockTest(MockTest mockTest) {
        this.mockTest = mockTest;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "MockQuestion{" +
                "id=" + id +
                ", mockTest=" + mockTest +
                ", question=" + question +
                '}';
    }
}
