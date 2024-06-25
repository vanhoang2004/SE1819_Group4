package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="classes")
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classcode")
    private Integer classcode;

    @Column(name = "classname")
    private String classname;

    public Classes(Integer classcode, String classname) {
        this.classcode = classcode;
        this.classname = classname;
    }

    public Classes() {
    }

    public Integer getClasscode() {
        return classcode;
    }

    public void setClasscode(Integer classcode) {
        this.classcode = classcode;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
