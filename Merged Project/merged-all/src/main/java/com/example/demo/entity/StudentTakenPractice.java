package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "studenttakenpractices")
public class StudentTakenPractice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studenttakenpracticeid")
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private Student student;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="teacherpracticeid", referencedColumnName = "teacherpracticeid")
    private TeacherPractice teacherpractice;
    @Column(name="score")
    private float score;
    public StudentTakenPractice(Student student, TeacherPractice teacherpractice, float score) {

        this.student = student;
        this.teacherpractice = teacherpractice;
        this.score = score;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public TeacherPractice getTeacherpractice() {
        return teacherpractice;
    }
    public void setTeacherpractice(TeacherPractice teacherpractice) {
        this.teacherpractice = teacherpractice;
    }
    public float getScore() {
        return score;
    }
    public void setScore(float score) {
        this.score = score;
    }



}