package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "userid")
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private User user;

    @Column(name = "classcode")
    private Integer classcode;

    public Student() {
    }

    public Student(Integer userId, User user, Integer classcode) {
        this.userId = userId;
        this.user = user;
        this.classcode = classcode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getClasscode() {
        return classcode;
    }

    public void setClasscode(Integer classcode) {
        this.classcode = classcode;
    }
}
