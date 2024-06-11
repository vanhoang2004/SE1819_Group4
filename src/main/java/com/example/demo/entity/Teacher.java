package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Teachers")
public class Teacher {
    @Id
    @Column(name="UserID")
    private Integer userId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name="UserID")
    private User user;

    @Column(name="SubjectID", insertable = false, updatable = false)
    private Integer subjectId;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="SubjectID")
    private Subject subject;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
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

    public Teacher() {
    }
}
