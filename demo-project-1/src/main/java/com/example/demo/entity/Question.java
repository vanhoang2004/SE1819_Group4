package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table( name="questions")
public class Question {

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY )
        @Column(name="questionid")
    private Integer id;

    @Column(name="questiontitle")
    private String title;

    @Column(name="image")
    private String Image;

    @Column(name="option1")
    private String op1;

    @Column(name="option2")
    private String op2;

    @Column(name="option3")
    private String op3;

    @Column(name="option4")
    private String op4;

    @Column(name="answer")
    private String ans;

    @Column(name="subjectid")
    private String subjectID;

    @Column(name="chapterid")
    private String chapterID;

    @Column(name="levelid")
    private String levelID;

    @Column(name="status")
   private Integer status;

    public Question() {
    }

    public Question( String title, String image, String op1, String op2, String op3, String op4, String ans, String subjectID, String chapterID, String levelID, Integer status) {
        this.title = title;
        Image = image;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.ans = ans;
        this.subjectID = subjectID;
        this.chapterID = chapterID;
        this.levelID = levelID;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getChapterID() {
        return chapterID;
    }

    public void setChapterID(String chapterID) {
        this.chapterID = chapterID;
    }

    public String getLevelID() {
        return levelID;
    }

    public void setLevelID(String levelID) {
        this.levelID = levelID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
