package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;

@Entity
@Table(name = "Students")
public class Student {

    @Id
    @Column(name = "UserID")
    private int userId;

    @OneToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private User user;

    @Column(name = "Classcode")
    private int classcode;

    // Constructors, getters, and setters

    public Student() {
    }

    public Student(int userId, int classcode) {
        this.userId = userId;
        this.classcode = classcode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getClasscode() {
        return classcode;
    }

    public void setClasscode(int classcode) {
        this.classcode = classcode;
    }
}