package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="takenmocktest")
public class TakenMockTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="takenmocktestid")
    private Integer takenMockTestID;

    @ManyToOne
    @JoinColumn(name = "mocktestid", nullable = false)
    private MockTest mockTest;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column(name="score")
    private float score;

    public TakenMockTest() {
    }

    public TakenMockTest(MockTest mockTest, User user, float score) {
        this.mockTest = mockTest;
        this.user = user;
        this.score = score;
    }

    // Getters and setters
    public Integer getTakenMockTestID() {
        return takenMockTestID;
    }

    public void setTakenMockTestID(Integer takenMockTestID) {
        this.takenMockTestID = takenMockTestID;
    }

    public MockTest getMockTest() {
        return mockTest;
    }

    public void setMockTest(MockTest mockTest) {
        this.mockTest = mockTest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "TakenMockTest{" +
                "takenMockTestID=" + takenMockTestID +
                ", mockTest=" + mockTest +
                ", user=" + user +
                ", score=" + score +
                '}';
    }
}
