package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Students")
public class Student {
    @Id
    @Column(name="UserID")
    private Integer userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="UserID")
    private User user;

    @Column(name="Classcode", insertable = false, updatable = false)
    private Integer classCode;

    @ManyToOne
    @JoinColumn(name="Classcode")
    private Class sclass;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getClassCode() {
        return classCode;
    }

    public void setClassCode(Integer classCode) {
        this.classCode = classCode;
    }

    public Class getSclass() {
        return sclass;
    }

    public void setSclass(Class sclass) {
        this.sclass = sclass;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Student() {
    }
}