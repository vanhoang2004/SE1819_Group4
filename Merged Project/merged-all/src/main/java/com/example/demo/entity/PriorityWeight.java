package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="Priorityweight")
public class PriorityWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @OneToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private Student student;
    @OneToOne
    @JoinColumn(name = "questionid", referencedColumnName = "questionid")
    private Question question;
    @Column(name="weight")
    private int weight;
    public PriorityWeight(Student student, Question question, int weight) {
        this.student = student;
        this.question = question;
        this.weight = weight;
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
    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }


}
