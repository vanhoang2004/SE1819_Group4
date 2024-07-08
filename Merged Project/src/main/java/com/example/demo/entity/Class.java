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

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "teacherclass",
        joinColumns = @JoinColumn(name = "Classcode"),
        inverseJoinColumns = @JoinColumn(name = "UserID")
    )
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "nclass", cascade = CascadeType.ALL)
    private List<Notification> notifications;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "classsubject",
            joinColumns = @JoinColumn(name = "Classcode"),
            inverseJoinColumns = @JoinColumn(name = "SubjectID")
    )
    private List<Subject> subjects;

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

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

    @Override
    public String toString() {
        return "Class{" +
                "className='" + className + '\'' +
                ", classCode=" + classCode +
//                ", students=" + students +
//                ", teachers=" + teachers +
                '}';
    }
}
