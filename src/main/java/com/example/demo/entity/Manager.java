package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Managers")
public class Manager {
    @Id
    @Column(name="UserID")
    private int userId;

    @Column(name="SubjectID")
    private int subjectId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Manager(int userId, int subjectId) {
        this.userId = userId;
        this.subjectId = subjectId;
    }

    public Manager() {
    }
}
