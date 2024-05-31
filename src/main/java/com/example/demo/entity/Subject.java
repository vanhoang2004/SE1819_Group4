package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="SubjectID")
    private Integer subjectId;

    @Column(name="Subjectname")
    private String subjectName;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public Subject() {
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
