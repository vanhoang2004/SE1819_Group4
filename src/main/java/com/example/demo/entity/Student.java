package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Students")
public class Student {
    @Id
    @Column(name = "UserID")
    private int userId;

    @Column(name = "Classcode")
    private int classCode;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getClassCode() {
        return classCode;
    }

    public void setClassCode(int classCode) {
        this.classCode = classCode;
    }

    public Student(int userId, int classCode) {
        this.userId = userId;
        this.classCode = classCode;
    }

    public Student() {
    }
}