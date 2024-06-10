package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Subjects")
public class Subject {

    @Id
    @Column(name="SubjectID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int subjectid;

    @Column(name="Subjectname")
    private String subjectname;



    public Subject(String subjectname) {
        this.subjectname = subjectname;
    }

    public Subject() {
    }

    public int getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(int subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }


    }

