package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="teacherpractice")
public class TeacherPractice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="teacherpracticeid")
    private Integer teacherpracticeid;

    @Column(name="title")
    private String title;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "classcode", referencedColumnName = "classcode")
    private Classes classes;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "subjectid", referencedColumnName = "subjectid")
    private Subject subject;

    @Column(name="deadline")
    private LocalDateTime deadline;


    @Column(name="publishdate")
    private LocalDateTime publishdate;

    public TeacherPractice() {
    }

    public TeacherPractice(String title, Classes classes, Subject subject, LocalDateTime deadline, LocalDateTime publishdate) {
        this.title = title;
        this.classes = classes;
        this.subject = subject;
        this.deadline = deadline;
        this.publishdate = publishdate;
    }

    public Integer getTeacherpracticeid() {
        return teacherpracticeid;
    }

    public void setTeacherpracticeid(Integer teacherpracticeid) {
        this.teacherpracticeid = teacherpracticeid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(LocalDateTime publishdate) {
        this.publishdate = publishdate;
    }
}
