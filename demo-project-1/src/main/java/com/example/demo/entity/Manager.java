package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="managers")
public class Manager {

    @Id
    @Column(name="userid")
    private Integer userid;
    @Column(name="subjectid")
    private Integer subjectid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    public Manager() {
    }

    public Manager(Integer userid, Integer subjectid) {
        this.userid = userid;
        this.subjectid = subjectid;
    }
}
