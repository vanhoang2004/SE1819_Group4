package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapterid")
    private Integer id;

    @Column(name="chaptername")
    private String name;

    @Column(name="subjectid")
    private  Integer subjectID;

    public Chapter() {
    }

    public Chapter(String name, Integer subjectID) {
        this.name = name;
        this.subjectID = subjectID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
    }
}
