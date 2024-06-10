package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="materials")
public class Material {
    @Id
    @Column(name="materialid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int materialid;

    @Column(name="subjectid")
    private int subjectid;

    @Column(name="chapterid")
    private int chapterid;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    public Material() {
    }

    public Material(int materialid, int subjectid, int chapterid, String title, String content) {
        this.materialid = materialid;
        this.subjectid = subjectid;
        this.chapterid = chapterid;
        this.title = title;
        this.content = content;
    }

    public int getMaterialid() {
        return materialid;
    }

    public void setMaterialid(int materialid) {
        this.materialid = materialid;
    }

    public int getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(int subjectid) {
        this.subjectid = subjectid;
    }

    public int getChapterid() {
        return chapterid;
    }

    public void setChapterid(int chapterid) {
        this.chapterid = chapterid;
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
}
