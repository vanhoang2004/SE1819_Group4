package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Mocktests")
public class Mocktest {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="MocktestiD")
    private int id;

    @Column(name="Mocktesttitle")
    private String title;

    @Column(name="SubjectID")
    private int subjectId;

    @Column(name="Start")
    private LocalDateTime start;

    @Column(name="End")
    private LocalDateTime end;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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

    public Mocktest(String title, int subjectId, LocalDateTime start, LocalDateTime end) {
        this.title = title;
        this.subjectId = subjectId;
        this.start = start;
        this.end = end;
    }

    public Mocktest() {
    }
}
