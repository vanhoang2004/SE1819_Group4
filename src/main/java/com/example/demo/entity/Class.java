package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Classes")
public class Class {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Classcode")
    private Integer classCode;

    @Column(name="Classname")
    private String className;

    @OneToMany(mappedBy = "sclass")
    private List<Student> students;

    public Integer getClassCode() {
        return classCode;
    }

    public void setClassCode(Integer classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Class() {
    }
}
