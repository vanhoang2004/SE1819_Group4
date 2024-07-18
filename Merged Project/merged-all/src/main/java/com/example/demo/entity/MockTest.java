package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="mocktests")
public class MockTest {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="mocktestid")
    private Integer id;

    @Column(name = "mocktesttitle")
    private String title;

    @Column(name = "subjectid")
    private Integer subjectId;

    @Column(name = "start")
    private LocalDateTime start;

    @Column(name = "end")
    private LocalDateTime end;
       public MockTest() {
    }


    // manytoone

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinTable(
            name = "mocktestdetails",
            joinColumns = @JoinColumn(name = "mocktestid"),
            inverseJoinColumns = @JoinColumn(name = "questionid")
    )
    private List<Question> likedQuestion;

    @OneToMany(mappedBy = "mockTest", cascade = CascadeType.ALL)
    private List<MockQuestion> mockQuestions;
    @OneToMany(mappedBy = "mockTest", cascade = CascadeType.ALL)
    private List<TakenMockTest> takenMockTests;

    public MockTest(String title, Integer subjectId, LocalDateTime start, LocalDateTime end) {
        this.title = title;
        this.subjectId = subjectId;
        this.start = start;
        this.end = end;
    }
    // Getters and setters

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "MockTest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subjectId=" + subjectId +
                ", start=" + start +
                ", end=" + end +
//                ", likedQuestion=" + likedQuestion +
                '}';
    }
}
