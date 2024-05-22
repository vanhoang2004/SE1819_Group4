package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Admins")
public class Admin {
    @Id
    @Column(name="UserID")
    private int userId;

    @Column(name="Title")
    private String title;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Admin(int userId, String title) {
        this.userId = userId;
        this.title = title;
    }

    public Admin() {
    }
}
