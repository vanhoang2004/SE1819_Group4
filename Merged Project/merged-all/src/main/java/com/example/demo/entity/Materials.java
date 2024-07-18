package com.example.demo.entity;


import jakarta.persistence.*;

@Entity
@Table(name="materials")
public class Materials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="materialid")
    private Integer id;

    @Column(name="subjectid")
    private Integer subjectID;

    @Column(name="chapterid")
    private Integer chapterID;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    public Materials() {
    }

    public Materials( Integer subjectID, Integer chapterID, String title, String content) {
        this.subjectID = subjectID;
        this.chapterID = chapterID;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
    }

    public Integer getChapterID() {
        return chapterID;
    }

    public void setChapterID(Integer chapterID) {
        this.chapterID = chapterID;
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

    @Transient
    public String  getMaterialsPath(){
        if(content==null || id==null) return null;
        return content;
    }
}
