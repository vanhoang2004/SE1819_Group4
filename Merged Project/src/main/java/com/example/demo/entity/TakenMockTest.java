package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="takenmocktest")
public class TakenMockTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="takenmocktestid")
    private Integer takenMockTestID;

    @Column(name="mocktestid")
    private Integer mockTestID;

    @Column(name="userid")
    private Integer userID;

    @Column(name="score")
    private float score;

    public TakenMockTest() {
    }

    public TakenMockTest(int mockTestID, int userID, float score) {
        this.mockTestID = mockTestID;
        this.userID = userID;
        this.score = score;
    }

    public int getTakenMockTestID() {
        return takenMockTestID;
    }

    public void setTakenMockTestID(int takenMockTestID) {
        this.takenMockTestID = takenMockTestID;
    }

    public int getMockTestID() {
        return mockTestID;
    }

    public void setMockTestID(int mockTestID) {
        this.mockTestID = mockTestID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }


}
