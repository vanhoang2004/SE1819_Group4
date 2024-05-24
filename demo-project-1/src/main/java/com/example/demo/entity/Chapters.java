package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chapters")
public class Chapters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapterid")
    private Integer id;

    @Column(name="chaptername")
    private String name;

    @Column(name="subjectid")
    private  Integer subjectID;

    public Chapters() {
    }

    public Chapters(String name, Integer subjectID) {
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
