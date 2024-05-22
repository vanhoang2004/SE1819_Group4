package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Classes")
public class Class {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Classcode")
    private int classCode;

    @Column(name="Classname")
    private String className;

    public int getClassCode() {
        return classCode;
    }

    public void setClassCode(int classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Class(int classCode, String className) {
        this.classCode = classCode;
        this.className = className;
    }

    public Class() {
    }
}
