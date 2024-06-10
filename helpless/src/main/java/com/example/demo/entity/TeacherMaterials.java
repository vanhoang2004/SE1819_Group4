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

    @Column(name="classcode")
    private Integer classcode;

    @Column(name="subjectid")
    private Integer subjectid;

    public TeacherMaterials() {
    }

    public TeacherMaterials(Integer teachermaterialid, String title, String content, Integer classcode, Integer subjectid) {
        this.teachermaterialid = teachermaterialid;
        this.title = title;
        this.content = content;
        this.classcode = classcode;
        this.subjectid = subjectid;
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
}
