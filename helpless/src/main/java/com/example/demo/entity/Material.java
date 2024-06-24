package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="materials")
public class Material {
    @Id
    @Column(name="materialid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int materialid;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "subjectid", referencedColumnName = "subjectid")
    private Subject subject;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="chapterid", referencedColumnName ="chapterid")
    private Chapter chapter;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    public Material() {
    }

    public Material(Subject subject, Chapter chapter, String title, String content) {
        this.subject = subject;
        this.chapter = chapter;
        this.title = title;
        this.content = content;
    }

    public int getMaterialid() {
        return materialid;
    }

    public void setMaterialid(int materialid) {
        this.materialid = materialid;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
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
