package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="mocktests")
public class MockTest {

    @Id
    @Column(name="mocktestid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int mocktestid;

    @Column(name="mocktesttitle")
    private String mocktesttitle;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "subjectid", referencedColumnName = "subjectid")
    private Subject subject;

    @Column(name="start")
    private LocalDateTime start;

    @Column(name="end")
    private LocalDateTime end;

    public MockTest() {
    }

    public MockTest(String mocktesttitle, Subject subject, LocalDateTime start, LocalDateTime end) {
        this.mocktesttitle = mocktesttitle;
        this.subject = subject;
        this.start = start;
        this.end = end;
    }

    public int getMocktestid() {
        return mocktestid;
    }

    public void setMocktestid(int mocktestid) {
        this.mocktestid = mocktestid;
    }

    public String getMocktesttitle() {
        return mocktesttitle;
    }

    public void setMocktesttitle(String mocktesttitle) {
        this.mocktesttitle = mocktesttitle;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

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
}

