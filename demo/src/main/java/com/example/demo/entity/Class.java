package com.example.demo.entity;

import java.util.List;



import jakarta.persistence.*;

@Entity
@Table(name="Classes")
public class Class {

    @Id
    @Column(name="Classcode")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int classcode;

    @Column(name="Classname")
    private String classname;
    



    public Class(String classname) {
        this.classname = classname;
    }

    public Class() {
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