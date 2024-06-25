package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Managers")
public class Manager {
    @Id
    @Column(name="UserID")
    private Integer userId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name="UserID")
    private User user;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="SubjectID")
    private Subject subject;

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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Manager() {
    }

    @Override
    public String toString() {
        return "Manager{" +
                "userId=" + userId +
                ", user=" + user +
                ", subject=" + subject +
                '}';
    }
}
