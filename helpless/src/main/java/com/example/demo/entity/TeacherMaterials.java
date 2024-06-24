package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Teachermaterials")
public class TeacherMaterials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="teachermaterialid")
    private Integer teachermaterialid;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "classcode", referencedColumnName = "classcode")
    private Classes classes;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "subjectid", referencedColumnName = "subjectid")
    private Subject subject;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private User user;

    public TeacherMaterials() {
    }

    public TeacherMaterials(String title, String content, Classes classes, Subject subject, User user) {
        this.title = title;
        this.content = content;
        this.classes = classes;
        this.subject = subject;
        this.user = user;
    }

    public Integer getTeachermaterialid() {
        return teachermaterialid;
    }

    public void setTeachermaterialid(Integer teachermaterialid) {
        this.teachermaterialid = teachermaterialid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
