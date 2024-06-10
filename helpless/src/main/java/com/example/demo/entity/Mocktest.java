package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="mocktests")
public class Mocktest {

    @Id
    @Column(name="mocktestid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int mocktestid;

    @Column(name="mocktesttitle")
    private String mocktesttitle;

    @Column(name="subjectid")
    private String subjectId;

    @Column(name="start")
    private String start;

    @Column(name="end")
    private String end;

    public Mocktest() {
    }

    public Mocktest(String mocktesttitle, String subjectId, String start, String end) {

        this.mocktesttitle = mocktesttitle;
        this.subjectId = subjectId;
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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }




}

