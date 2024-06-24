package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Classes")
public class Classes {

    @Id
    @Column(name="Classcode")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int classcode;

    @Column(name="Classname")
    private String classname;



    public Classes(String classname) {
        this.classname = classname;
    }

    public Classes() {
    }

    public int getClasscode() {
        return classcode;
    }

    public void setClasscode(int classcode) {
        this.classcode = classcode;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }


}

