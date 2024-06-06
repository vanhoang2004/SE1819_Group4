package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="teacherpractice")
public class TeacherPractice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="teacherpracticeid")
    private Integer teacherpracticeid;

    @Column(name="title")
    private String title;

    @Column(name="classcode")
    private Integer classcode;

    @Column(name="subjectid")
    private Integer subjectid;

    @Column(name="deadline")
    private String deadline;


    @Column(name="publishdate")
    private String publishdate;

    public TeacherPractice() {
    }

    public TeacherPractice(Integer teacherpracticeid, String title, Integer classcode, Integer subjectid, String deadline, String publishdate) {
        this.teacherpracticeid = teacherpracticeid;
        this.title = title;
        this.classcode = classcode;
        this.subjectid = subjectid;
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

    public Integer getClasscode() {
        return classcode;
    }

    public void setClasscode(Integer classcode) {
        this.classcode = classcode;
    }

    public Integer getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }
}
