package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Teachers")
public class Teacher {
    @Id
    @Column(name="UserID")
    private Integer userId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name="UserID")
    private User user;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="SubjectID")
    private Subject subject;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "teacherclass",
            joinColumns = @JoinColumn(name = "UserID"),
            inverseJoinColumns = @JoinColumn(name = "Classcode")
    )
    private List<Class> classes;

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "userId=" + userId +
                ", user=" + user +
                ", subject=" + subject +
                ", classes=" + classes +
                '}';
    }
}
