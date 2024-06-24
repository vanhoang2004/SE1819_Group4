package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Teachers")
public class Teacher {

    @Id
    @Column(name = "UserID")
    private int userId;

    @OneToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "subjectid", referencedColumnName = "subjectid")
    private Subject subject;

    public Teacher() {
    }

    public Teacher(Subject subject, User user) {
        this.subject = subject;
        this.user = user;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}


